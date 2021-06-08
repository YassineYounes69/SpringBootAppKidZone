package com.example.kidszonea4arctic3.utilis;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.kidszonea4arctic3.models.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class UserPDFExporter {

    private ArrayList<Employee> listUsers;
    private String cccname ;

    public UserPDFExporter(ArrayList<Employee> listUsers ,String cccname) {
        this.listUsers = listUsers;
        this.cccname = cccname;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Employee ID", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Enabled", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Employee user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getfName()+" "+user.getlName());
            table.addCell(user.getRole());
            table.addCell("true");
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        //header
        HeaderFooter header = new HeaderFooter(new Phrase(cccname),false);
        header.setAlignment(HeaderFooter.ALIGN_CENTER);
        header.setBorder(Rectangle.NO_BORDER);
        header.setBorder(Rectangle.BOTTOM);
        document.setHeader(header);

        //Foooter
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currentDateTime = dateFormatter.format(new Date());
        HeaderFooter footer = new HeaderFooter(new Phrase(currentDateTime),false);
        footer.setAlignment(HeaderFooter.ALIGN_RIGHT);
        footer.setBorder(Rectangle.NO_BORDER);
        footer.setBorder(Rectangle.TOP);
        document.setFooter(footer);



        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Employees", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);


        document.add(p);


        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
