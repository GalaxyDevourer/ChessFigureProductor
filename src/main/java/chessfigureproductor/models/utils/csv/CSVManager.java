package chessfigureproductor.models.utils.csv;

import chessfigureproductor.models.table.FigureStepData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface CSVManager {
    default void writeToCSV (List<FigureStepData> data, String path) {
        try(FileWriter writer = new FileWriter(path + "/" + "GeneratedRules.csv", false))
        {
            data.forEach( x -> {
                String sub_str = x.getNumber() + ";" + x.getCurrent() + ";" + x.getDestination() + ";\n";
                try {
                    writer.write(sub_str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

//    default String[][] readFromCSV (String path, String fileName, int size) {
//        List<List<String>> lists = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(path + "/" + fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(";", -1);
//                //System.out.println(Arrays.toString(values));
//                lists.add(Arrays.asList(values));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String[][] table = new String[size][size];
//        for (int i = 0; i < lists.size(); i ++) {
//            table[i] = (String[]) lists.get(i).toArray();
//        }
//
//        return table;
//    }
}
