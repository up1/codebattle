import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;
import java.util.List;

public class best_script {

	public final static int SIZE_OF_NUMBER = 10000;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		if (args != null && args.length == 1) {
			String inputFile = args[0];
			writeResultToFile(findMissingNumbers(readInputFromFile(inputFile)), "run_result.txt");
			System.out.println("Total Nano time=" + (System.nanoTime() - startTime));
		} else {
			System.out.println("Please run =>$java best_script.java <input-filename>");
		}
	}

	private static List<String> readInputFromFile(String inputFile) {
		long startTime = System.nanoTime();
		Path filePath = new File(inputFile).toPath();
		Charset charset = Charset.defaultCharset();
		List<String> inputs = null;
		try {
			inputs = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Time for readInputFromFile=" + (System.nanoTime() - startTime));
		return inputs;
	}

	private static int[] findMissingNumbers(List<String> inputs) {
		long startTime = System.nanoTime();
		BitSet bitSet = new BitSet(SIZE_OF_NUMBER);
		for (String input : inputs) {
			bitSet.set(Integer.valueOf(input, 10).intValue());
		}
		int numberOfMissing = SIZE_OF_NUMBER - inputs.size();
		int[] missingNumbers = new int[numberOfMissing];
		for (int counter = 0; counter < numberOfMissing; counter++) {
			int index = (counter == 0) ? 0 : (missingNumbers[counter - 1]);
			missingNumbers[counter] = bitSet.nextClearBit(index + 1);
		}
		System.out.println("Time for findMissingNumbers=" + (System.nanoTime() - startTime));
		return missingNumbers;
	}

	private static void writeResultToFile(int[] results, String fileName) {
		long startTime = System.nanoTime();
		try {
			PrintWriter pr = new PrintWriter(fileName);
			for (int result : results) {
				pr.println(String.format("%04d", result));
			}
			pr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Time for writeResultToFile=" + (System.nanoTime() - startTime));
	}

}
