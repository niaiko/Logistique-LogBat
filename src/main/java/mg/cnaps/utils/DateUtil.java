package mg.cnaps.utils;

public class DateUtil {
	public static java.sql.Date sqlDateNow() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}
