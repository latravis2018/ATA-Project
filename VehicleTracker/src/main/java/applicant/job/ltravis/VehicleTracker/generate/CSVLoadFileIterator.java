package applicant.job.ltravis.VehicleTracker.generate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/***
 * Iterate over the lines of a CSV file, returning an array of strings for each column found.
 * 
 * @author latravis
 * 
 */
public class CSVLoadFileIterator {

	private FileReader freader = null;
	private BufferedReader reader = null;
	private CSVParser parser = null;
	private Iterator<CSVRecord> iterator;
	
	public CSVLoadFileIterator(String filename) throws FileNotFoundException {
		
		freader = new FileReader(filename);
		reader = new BufferedReader(freader);

		try {
			parser = new CSVParser(reader, CSVFormat.DEFAULT);
			iterator = parser.iterator();
			// Throw away the first line which is column headings
			if (iterator.hasNext()) {
				iterator.next();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String[] cleanup(String[] cols) {
		for (int i = 0; i < cols.length; i++) {
			cols[i] = cols[i].replace("\"", "");
		}
		return cols;
	}
	
/*	private String[] csvSplit(String line) {
		ArrayDeque<String> ad = new ArrayDeque<String>();
		int commaIndex = -1;
		int quoteIndex = -1;
		
	}
*/	
	public String[] next() throws IOException {
		String cols[] = null;
		CSVRecord rec;
		
		if (iterator.hasNext()) {
			rec = iterator.next();
			cols = new String[rec.size()];
			for (int i = 0; i < rec.size(); i++) {
				cols[i] = rec.get(i);
			}
		}
		
/*		String line = null;
		if ((line = reader.readLine()) != null)
		{
			cols = line.split(",");
			
			cols = cleanup(cols);
		}
*/
		return cols;
	}
	
	public void close() throws IOException {
		reader.close();
		if (parser != null)  {
			parser.close();
		}
	}
	
}
