package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
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
	
	
	public static Signature getSignatureBy(int versionId, int userId) throws SQLException {
		HashMap params = new HashMap();
		params.put("versionId", versionId);
		params.put("userId", userId);
		return (Signature)IBatisManager.sqlMapper.queryForObject("getSignatureBy", params);
	}

	public static boolean exist(int versionId, int userId) throws SQLException {
		return getSignatureBy(versionId, userId) != null;
	}
}
