package jadx.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

public class Utils {

	private Utils() {
	}

	public static String cleanObjectName(String obj) {
		int last = obj.length() - 1;
		if (obj.charAt(0) == 'L' && obj.charAt(last) == ';') {
			return obj.substring(1, last).replace('/', '.');
		}
		return obj;
	}

	public static String makeQualifiedObjectName(String obj) {
		return 'L' + obj.replace('.', '/') + ';';
	}

	public static String escape(String str) {
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			switch (c) {
				case '.':
				case '/':
				case ';':
				case '$':
				case ' ':
				case ',':
				case '<':
					sb.append('_');
					break;

				case '[':
					sb.append('A');
					break;

				case ']':
				case '>':
				case '?':
				case '*':
					break;

				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	public static String listToString(Iterable<?> list) {
		if (list == null) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
			Object o = it.next();
			str.append(o);
			if (it.hasNext()) {
				str.append(", ");
			}
		}
		return str.toString();
	}

	public static String arrayToString(Object[] array) {
		if (array == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String getStackTrace(Throwable throwable) {
		if (throwable == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	public static int compare(int x, int y) {
		return x < y ? -1 : x == y ? 0 : 1;
	}
}
