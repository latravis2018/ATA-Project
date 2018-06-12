package applicant.job.ltravis.VehicleTracker.generate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVSaveFileIterator {
	private FileWriter fwriter = null;
	private BufferedWriter writer = null;
	private StringBuilder sb = new StringBuilder();

	public CSVSaveFileIterator(String filename) throws IOException {
		
		fwriter = new FileWriter(filename);
		writer = new BufferedWriter(fwriter);
	}
	
	public void next(String[] cols) throws IOException
	{
		String delimChar = "";
		
		sb.setLength(0);
		
		for (int i = 0; i < cols.length; i++) {
			if (cols[i].contains(","))
			{
				delimChar = "\"";
				sb.append(delimChar);
			}
			sb.append(cols[i]);
			
			if (delimChar.length() > 0) {
				sb.append(delimChar);
				delimChar = "";
			}
			
			if (i < cols.length-1) {
				sb.append(',');
			}
		}
		writer.write(sb.toString());
		writer.newLine();
	}
	
	public void close() throws IOException {
		writer.close();
	}
}
