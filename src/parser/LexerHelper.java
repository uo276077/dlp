package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
		if(str.charAt(1) == '\\'){
			if(str.charAt(2) != 'n' && str.charAt(2) != 't') {
				return (char) Integer.parseInt(str.substring(2, str.length()-1));
			}
			char result;
			if(str.charAt(2) == 'n') result = '\n';
			else result = '\t';
			return result;
		}
		return str.charAt(1);
	}

	public static double lexemeToReal(String str) {
		return Double.valueOf(str);
	}
}
