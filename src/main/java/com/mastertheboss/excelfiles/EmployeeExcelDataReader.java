package com.mastertheboss.excelfiles;


import com.mastertheboss.application.QueryExecutor;
import com.mastertheboss.Constants.Gender;
import com.mastertheboss.framework.Configuration;
import com.mastertheboss.util.ConvertFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class EmployeeExcelDataReader {
    private static final String SourcePath = Configuration.of().getPath("text")+ "\\";

    public static void updateDBWithExcel(){

        try {
            File file = new File(SourcePath+"Employee.xlsx");
            FileInputStream input = new FileInputStream(file);

            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator< Row > itr = sheet.iterator();

            itr.next();//skip the first row
            while (itr.hasNext()){
                Row row = itr.next();
                Iterator< Cell > cellIterator = row.cellIterator();

                List<String> ls = new ArrayList<>();
                Date birthday = null;
                Gender gender = null;



                int j=0;
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    if (j<7 && j!=2){
                        ls.add(cell.getStringCellValue());
                    }else if (j==2){
                        gender = Gender.valueOf(cell.getStringCellValue());
                    }else if (j==7){birthday=cell.getDateCellValue();}
                    j++;
                }
                for (int i =0 ; i< 6; i++) {
                    if (ls.get(i).equals("null")) ls.set(i, null);
                }
                new QueryExecutor().addnewEmployee(ls.get(0),
                        ls.get(1),
                        gender,
                        ls.get(2),
                        ls.get(3),
                        ls.get(4),
                        ls.get(5),
                        ConvertFormats.convertToLocalDateViaInstant(birthday));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void test(){
        updateDBWithExcel();
    }
}
