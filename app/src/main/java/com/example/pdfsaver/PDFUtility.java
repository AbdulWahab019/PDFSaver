package com.example.pdfsaver;

import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressWarnings("ResultOfMethodCallIgnored")
class PDFUtility {

    // Method call by MainActivity to savePDF file.
    void pdfSaver() {
        setDocument();
    }

    // Method to set the document attributes, and save data
    private void setDocument() {
        Document document = new Document();
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));

        // Getting File
        File file = getFile();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setting document Attributes
        document.open();
        document.setPageSize(PageSize.A4);
        document.addCreationDate();

        for(int i = 0; i < MainActivity.list.size(); i++) {
            // Writing data in pdf File in required format
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph label = new Paragraph("Name"); // will print on left
            label.add(new Chunk(glue));
            label.add("Contact Number"); // will print on right

            Paragraph value = new Paragraph(MainActivity.list.get(i).getName()); // left
            value.add(new Chunk(glue));
            value.add(MainActivity.list.get(i).getContactNumber()); // right

            try {
                // Adding to document
                document.add(label);
                document.add(value);
                document.add(new Paragraph(""));
                document.add(new Chunk(lineSeparator));
                document.add(new Paragraph(""));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();
    }

    // Method that returns the file
    private File getFile(){
        File file = new File(Environment.getExternalStorageDirectory().getPath()+ "/Report.pdf");
        if(!file.exists()){
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
