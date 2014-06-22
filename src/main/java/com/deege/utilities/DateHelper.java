package com.deege.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Helper class to convert date from Date to GregorianCalendar and vice versa.</p>
 * 
 */
public class DateHelper {
	/**
	 * <p>Needed to create XMLGregorianCalendar instances</p>
	 */
	private static DatatypeFactory df = null;

	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new IllegalStateException(
					"Error while trying to obtain a new instance of DatatypeFactory",
					e);
		}
	}

	/**
	 * <p>Converts an XMLGregorianCalendar to an instance of {@link java.util.Date}</p>
	 * 
	 * @param xgc Instance of XMLGregorianCalendar or a null reference
	 * @return java.util.Date instance whose value is based upon the value in
	 *         the xgc parameter. If the xgc parameter is null then this method
	 *         will simply return null.
	 */
	public static java.util.Date asDate(XMLGregorianCalendar xgc) {
		if (xgc == null) {
			return null;
		} else {
			return xgc.normalize().toGregorianCalendar().getTime();
		}
	}

	/**
	 * <p>Converts a java.util.Date into an instance of {@link XMLGregorianCalendar}</p>
	 * 
	 * @param date Instance of java.util.Date or a null reference
	 * @return XMLGregorianCalendar instance whose value is based upon the value
	 *         in the date parameter. If the date parameter is null then this
	 *         method will simply return null.
	 */
	public static XMLGregorianCalendar asXMLGregorianCalendar(
			java.util.Date date) {
		if (date == null) {
			return null;
		} else {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
			return df.newXMLGregorianCalendar(gc);
		}
	}

	/**
	 * <p>Returns a {@link java.util.Date} without the time.</p>
	 * 
	 * @param date to strip of time
	 * @return date without time
	 */
	public static Date getDateWithoutTime(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	/**
	 * <p>Returns a {@link java.util.Date} that represents the last millisecond of
	 * a particular day. (end of day)</p>
	 * 
	 * @param date
	 * @return end of the day
	 */
	public static Date getEndOfDay(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}
}
