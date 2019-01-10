import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Huffman {
	private static final char ALPHABET_START = 'A';
	private static final char ALPHABET_END = 'Z';
	private static final char[] OTHER_SYMBOLS = { ' ' };

	private static final long ALPHABET_LENGTH = ALPHABET_END - ALPHABET_START + 1;
	private static final long EXPECTED_LINE_COUNT = ALPHABET_LENGTH + OTHER_SYMBOLS.length + 1;

	private static final String LINE_REG_EX = "(0|1)*";

	private static final String LINE_COUNT_ERROR_TEXT = "Expected line count %d but was %d.";
	private static final String PARSING_ERROR_TEXT = "Parsing Error in following lines (must matsch ): ";

	public static String decode(File f) {
		ExtractedHuffmanInfo ehi = new ExtractedHuffmanInfo();
		loadInformationFromFile(f, ehi);

		if (ehi.getLineCount() != EXPECTED_LINE_COUNT) {
			throw new IllegalArgumentException(
					String.format(LINE_COUNT_ERROR_TEXT, EXPECTED_LINE_COUNT, ehi.getLineCount()));
		} else if (ehi.parsingErrorOccurred()) {
			throw new IllegalArgumentException(PARSING_ERROR_TEXT + ehi.getFailedLines());
		}

		decodeWithMapping(ehi);

		return ehi.getDecodedMessage();
	}

	private static void loadInformationFromFile(File file, ExtractedHuffmanInfo ehi) {
		try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
			lines.forEachOrdered(ehi);
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private static void decodeWithMapping(ExtractedHuffmanInfo ehi) {
		int pos = 0;
		int len = 0;

		while (ehi.getEncodedMessage() != null && pos < ehi.getEncodedMessage().length()) {
			if (ehi.getMapping().containsKey(ehi.getEncodedMessage().substring(pos, pos + len))) {
				ehi.appendToDecodedMessage(ehi.getMapping().get(ehi.getEncodedMessage().substring(pos, pos + len)));
				pos += len;
				len = 0;
			} else {
				len++;
			}
		}
	}

	public static void main(String args[]) {
		System.out.println(decode(new File("message.txt")));
	}

	private static class ExtractedHuffmanInfo implements Consumer<String> {
		private boolean errorFlag;
		private StringBuilder linesWithError;

		private long lineCount;

		private String encodedMessage;
		private StringBuilder decodedMessage;
		private HashMap<String, Character> mapping;
		
		ArrayList<Character> symbols;

		public ExtractedHuffmanInfo() {
			errorFlag = false;
			linesWithError = new StringBuilder();

			lineCount = 0;

			encodedMessage = null;
			decodedMessage = new StringBuilder();
			mapping = new HashMap<>();
			
			symbols = new ArrayList<>();
			for(char i = ALPHABET_START; i <= ALPHABET_END; ++i) {
				symbols.add(i);
			}
			for(char c : OTHER_SYMBOLS) {
				symbols.add(c);
			}
		}

		public void accept(String line) {
			lineCount++;
			if (!isValid(line)) {
				errorFlag = true;
				linesWithError.append(lineCount);
				linesWithError.append(", ");
			} else if(lineCount == 1) {
				encodedMessage = line;
			} else if(!line.isEmpty()) {
				mapping.put(line, lineCountToChar());
			}
		}

		public void appendToDecodedMessage(char append) {
			decodedMessage.append(append);
		}

		public boolean parsingErrorOccurred() {
			return errorFlag;
		}

		public String getFailedLines() {
			if (linesWithError.length() > 1) {
				linesWithError.setLength(linesWithError.length() - 2);
			}
			return linesWithError.toString();
		}

		public long getLineCount() {
			return lineCount;
		}

		public String getEncodedMessage() {
			return encodedMessage;
		}

		public String getDecodedMessage() {
			return decodedMessage.toString();
		}

		public HashMap<String, Character> getMapping() {
			return mapping;
		}

		private static boolean isValid(String line) {
			return line.matches(LINE_REG_EX);
		}

		private char lineCountToChar() {
			return symbols.get((int) (lineCount - 2));
		}
	}
}
