package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.Signature;

public class SignatureDAO {

	public static Signature getSignatureBlobBy(Signature signature) throws SQLException {
		return (Signature)IBatisManager.sqlMapper.queryForObject("getSignatureByVersionIdAndUserId", signature);
	}
	
	public static void insertSignature(Signature signature) throws SQLException {
		IBatisManager.sqlMapper.insert("insertSignature", signature);
	}
	
	/*List<SignatureVO>*/
	public static List selectSignaturesFor(int versionID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectSignaturesForVersion", versionID);
	}
}
