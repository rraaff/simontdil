package com.tdil.simon.test.smtp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * Class <code>SMTPServer</code> is a SMTPServer that receives all mails
 * and print them to the console.
 * 
 * @author mgodoy
 *
 */
public class SMTPServer {

	private static final String CONTENT_DISPOSITION_INLINE = "Content-Disposition: inline";
	private static final String CONTENT_DISPOSITION_FINISH = "Part";
	private static final String TO = "To:";
	private static final String FROM = "From:";
	private static final String SUBJECT = "Subject:";
	// General Message
	private static final String WELCOME_MESSAGE = "220 Welcome ? to LocalHost Java SMTP Server.";
//	private static final String MESSAGE_DISCONNECT = "221 SMTP server signing off.";
	private static final String MESSAGE_OK = "250 OK";
	private static final String MESSAGE_COMMAND_ORDER_INVALID = "503 Command not allowed here.";
//	private static final String MESSAGE_USER_NOT_LOCAL = "550 User does not exist.";
//	private static final String MESSAGE_USER_INVALID = "451 Address is invalid.";
	private static final String MESSAGE_SEND_DATA = "354 Start mail input; end with <CRLF>.<CRLF>";
	private static final String MESSAGE_SAVE_MESSAGE_ERROR = "500 Error handling message.";
	private static final String MESSAGE_INVALID_COMMAND = "500 Command Unrecognized: ";
//	private static final String MESSAGE_MESSAGE_TOO_LARGE = "552 Message size exceeds fixed maximum message size.";

	private static final ArrayList<Email> allEmailsReceived = new ArrayList<Email>();
	private static SMTPServer instance = new SMTPServer();
	
	// Commands
	private static final String COMMAND_HELO = "HELO";
	private static final String COMMAND_EHLO = "EHLO";
	private static final String COMMAND_RSET = "RSET";
	private static final String COMMAND_NOOP = "NOOP";
	private static final String COMMAND_QUIT = "QUIT";
	private static final String COMMAND_MAIL_FROM = "MAIL";
	private static final String COMMAND_RCPT_TO = "RCPT";
	private static final String COMMAND_DATA = "DATA";

	// SMTP Commands
	public int NONE = 0;
	public int HELO = 1;
	public int QUIT = 2;
	public int MAIL_FROM = 3;
	public int RCPT_TO = 4;
	public int DATA = 5;
	public int DATA_FINISHED = 6;
	public int RSET = 7;
	public int EHLO = 8;

	private ServerSocket socket;
	private boolean started;
	
	public static SMTPServer getSigleIntance (){
		return instance;
	}

	public SMTPServer() {
		super();
		new Thread("SMTP Server") {
			@Override
			public void run() {
				SMTPServer.this.startServer();
			}
		}.start();
		
	}
	
	private void startServer() {
		if (!started) {
		try {
			this.setSocket(new ServerSocket(2525));
			this.started = true;
			while (this.isStarted()) {
				Socket s = this.getSocket().accept();
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				InetAddress remoteAddress = s.getInetAddress();
                String clientIp = remoteAddress.getHostAddress();
				try {
					handleCommands(in, out, clientIp);
				} catch (RuntimeException e) {
					s.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	private String read(BufferedReader in) {
		try {
			String inputLine = in.readLine().trim();
			return inputLine;
		} catch (IOException ioe) {
			throw new RuntimeException();
		}
	}

	private void write(PrintWriter out, String message) {
		System.out.print(message + "\r\n");
		out.print( message + "\r\n" );
        out.flush();
	}

	private void handleCommands(BufferedReader in, PrintWriter out, String clientIp) {
		if (!started) {
			return;
		}
		String inputString;
		String command;
		String argument;

		int lastCommand = NONE;
		
		write(out, WELCOME_MESSAGE.replaceAll("\\?", clientIp));

		// This just runs until a SystemException is thrown, which
		// signals us to disconnect.
		while (true) {
			
			inputString = read(in);

			command = parseCommand(inputString);
			argument = parseArgument(inputString);

			if (command.equals(COMMAND_HELO) || command.equals(COMMAND_EHLO)) {
				write(out, "250 Hello " + argument);
				lastCommand = HELO;
			}
			// NOOP - Do Nothing.
			else if (command.equals(COMMAND_NOOP)) {
				write(out, MESSAGE_OK);
			}
			// Resets the state of the server back to the initial
			// state.
			else if (command.equals(COMMAND_RSET)) {
				write(out, MESSAGE_OK);
				lastCommand = RSET;
			}
			// Not only check the command, but the full string, since the
			// prepare command
			// method only returns the text before the first string, and this is
			// a two
			// word command.
			else if (command.equals(COMMAND_MAIL_FROM) && inputString.toUpperCase().startsWith("MAIL FROM:")) {
				
				if (lastCommand == HELO || lastCommand == NONE || lastCommand == RSET || lastCommand == EHLO) {
					if (handleMailFrom(out, inputString)) {
						lastCommand = MAIL_FROM;
					}
				} else {
					write(out, MESSAGE_COMMAND_ORDER_INVALID);
				}
			}
			// Not only check the command, but the full string, since the
			// prepare command
			// method only returns the text before the first string, and this is
			// a two
			// word command.
			else if (command.equals(COMMAND_RCPT_TO) && inputString.toUpperCase().startsWith("RCPT TO:")) {

				if (lastCommand == MAIL_FROM || lastCommand == RCPT_TO) {
					handleRcptTo(out, inputString);
					lastCommand = RCPT_TO;
				} else {
					write(out, MESSAGE_COMMAND_ORDER_INVALID);
				}
			} else if (command.equals(COMMAND_DATA)) {

				if (lastCommand == RCPT_TO) {
					handleData(in, out, clientIp);
					// Reset for another message
					lastCommand = RSET;
				} else {
					write(out, MESSAGE_COMMAND_ORDER_INVALID);
				}
			} else {
				write(out, MESSAGE_INVALID_COMMAND + command);
			}
		}
	}
    
	/**
     * Parses an address argument into a real email address.  This
     * method strips off any &gt; or &lt; symbols.
     */
    private String parseAddress( String address ) {

        int index = address.indexOf( "<" );
        if( index != -1 ) {
            address = address.substring( index + 1 );
        }
        index = address.indexOf( ">" );
        if( index != -1 ) {
            address = address.substring( 0, index );
        }
        return address;
    }
    
	/**
	 * Handle the "MAIL FROM:" command, which defines the sending address for
	 * this message.
	 * @param out 
	 */
	private boolean handleMailFrom(PrintWriter out, String inputString) {
		String fromAddress = parseAddress(inputString.substring(10));
		System.out.println("Mail From: " + fromAddress);
		write(out, MESSAGE_OK);
		return true;
	}

	/**
	 * Handle the "RCPT TO:" command, which defines one of the recieving
	 * addresses.
	 */
	private void handleRcptTo(PrintWriter out, String inputString) {
		String toAddress = parseAddress(inputString.substring(8));
		System.out.println("Mail To: " + toAddress);
		write(out, MESSAGE_OK);
	}

	/**
	 * Accepts the data being written to the socket.
	 * @param in 
	 * @param clientIp 
	 */
	private void handleData(BufferedReader in, PrintWriter out, String clientIp) {
		synchronized (instance) {
			// Get the current maxSize setting and convert to bytes.
			write(out, MESSAGE_SEND_DATA);
	
			// Add a datestamp to the message to track when the message arrived.
			System.out.println("X-RecievedDate: " + new Date());
			// Add a line to the message to track that the message when through this
			// server.
			System.out.println("Received: SMTP localhost from client: " + clientIp);
	
			try {
				String inputString = in.readLine();
				String from = "";
				String to = "";
				String subject = "";
				StringBuffer messageBuffer = new StringBuffer();
				boolean messageFlag = false;
				while (!inputString.equals(".")) {
					System.out.println(inputString);
					if (inputString.contains(FROM)){
						 from = StringUtils.deleteWhitespace(inputString.replace(FROM, ""));
					}
					if (inputString.contains(TO)){
						to = StringUtils.deleteWhitespace(inputString.replace(TO, ""));
					}
					if (inputString.contains(SUBJECT)){
						subject = StringUtils.deleteWhitespace(inputString.replace(SUBJECT , ""));
					}
					if (!messageFlag && inputString.contains(CONTENT_DISPOSITION_INLINE)){
						messageFlag = true;
					}
					if (messageFlag && !inputString.contains(CONTENT_DISPOSITION_FINISH)){
						messageBuffer.append(inputString);
					}
					inputString = in.readLine();
				}
				String message = cleanMessage(messageBuffer.toString());
				Email email = new Email(from, to, subject, message);
				allEmailsReceived.add(email);
			} catch (IOException ioe) {
				throw new RuntimeException();
			}
		}

		// Write the message to disk.
		try {
			write(out, MESSAGE_OK);
		} catch (Exception se) {
			write(out, MESSAGE_SAVE_MESSAGE_ERROR);
			throw new RuntimeException(se.getMessage());
		}
	}

	/**
	 * 
	 */
	private String cleanMessage(String messageValue){
		return messageValue.replace(CONTENT_DISPOSITION_INLINE, "");
	}
	
	/**
	 * Parses the input stream for the command. The command is the begining of
	 * the input stream to the first space. If there is space found, the entire
	 * input string is returned.
	 * <p>
	 * This method converts the returned command to uppercase to allow for
	 * easier comparison.
	 * <p>
	 * Additinally, this method checks to verify that the quit command was not
	 * issued. If it was, a SystemException is thrown to terminate the
	 * connection.
	 */
	private String parseCommand(String inputString) {

		int index = inputString.indexOf(" ");

		if (index == -1) {
			String command = inputString.toUpperCase();
			checkQuit(command);
			return command;
		} else {
			String command = inputString.substring(0, index).toUpperCase();
			checkQuit(command);
			return command;
		}
	}
	
    /**
     * Checks to make sure that the incoming command is not a quit.  If so,
     * the connection is terminated.
     */
    private void checkQuit( String command ) {
        if( command.equals( COMMAND_QUIT ) ) {
            System.out.println( "User has QUIT the session." );
            throw new RuntimeException();
        }
    }

	/**
	 * Parses the input stream for the argument. The argument is the text
	 * starting afer the first space until the end of the inputstring. If there
	 * is no space found, an empty string is returned.
	 * <p>
	 * This method does not convert the case of the argument.
	 */
	private String parseArgument(String inputString) {

		int index = inputString.indexOf(" ");

		if (index == -1) {
			return "";
		} else {
			return inputString.substring(index + 1);
		}
	}

	public static void main(String[] args) {
		new SMTPServer();
	}

	public ServerSocket getSocket() {
		return socket;
	}

	public void setSocket(ServerSocket socket) {
		this.socket = socket;
	}

	/**
	 * 
	 */
	public static void cleanAllMailsReceived(){
		synchronized (instance) {
			allEmailsReceived.clear();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Email> getAllMailsReceived(){
		synchronized (instance) {
			return allEmailsReceived;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static Email getEmailTo(String to){
		synchronized (instance) {
			for (Email email : getAllMailsReceived()) {
				if (email.getTo().equals(to)) {
					return email;
				}
			}
			return null;
		}
	}

	public boolean isStarted() {
		return started;
	}
	
	public void start() {
		this.started = true;
	}
	
	public void stop () throws IOException, InterruptedException{
		this.started = false;
//		try {
//			this.getSocket().close();
//		} catch (Exception e) {
//			// nothing to do
//		}
	}
	
}
