/*
Run file with this command :-
>>java -cp "Test.jar;lib/*" Main.java
*/


package com.saurav;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.xmlbeans.xml.stream.events.ElementTypeNames;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main
{
	static ArrayList<String> list = new ArrayList<String>();
    static HashSet<String> set = new HashSet();

    public static void focusedCrawlerUtil(String url, int depth, XSSFWorkbook allURLs, XSSFWorkbook allPTags, XSSFSheet urlSheet, XSSFSheet pSheet, int rowid1, int rowid2, int count)
    {
        if(depth == 600) {
            return;
        }
        System.out.println(url);

        Elements paragraphs = new Elements();
        Elements links = new Elements();

        try {
            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            paragraphs = body.getElementsByTag("p");
            links = doc.select("a[href]");
        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        for(Element link: links) {
            list.add(link.absUrl("href"));
        }
        if(url.contains("/faculty/"))
        {
            XSSFRow row = urlSheet.createRow(rowid1++);
            Cell cell = row.createCell(0);
            cell.setCellValue((String) "For Url: " + (count));
            cell = row.createCell(1);
            cell.setCellValue((String) url);
            for (Element link : links) {

                if (link.text().isEmpty() || link.absUrl("href").isEmpty()) {
                    continue;
                }
                row = urlSheet.createRow(rowid1++);
                cell = row.createCell(0);
                cell.setCellValue((String) link.text());
                cell = row.createCell(1);
                cell.setCellValue((String) link.absUrl("href"));
            }
            row = urlSheet.createRow(rowid1++);
            cell = row.createCell(0);
            cell.setCellValue((String) " ");
            cell = row.createCell(1);
            cell.setCellValue((String) " ");
        }

        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty()
                && !set.contains(link)
                && !link.contains("#")
                && !link.equals("https://alumni.pec.ac.in/")
                && !link.equals("http://alumni.pec.ac.in/")
                && !link.equals(url)
                && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))
                && !link.toLowerCase().contains(".docx")
                && !link.toLowerCase().contains(".pdf")
                && !link.toLowerCase().contains(".jpg")
                && !link.toLowerCase().contains(".doc")
                && !link.toLowerCase().contains(".xlsx")) {
                nexturl = link;
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("./fURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fURLs");
        }

        if(url.contains("/faculty/")) {
            XSSFRow row = pSheet.createRow(rowid2++);
            Cell cell = row.createCell(0);
            cell.setCellValue((String) "For Url: " + (count));
            cell = row.createCell(1);
            count++;
            cell.setCellValue((String) url);
            for(Element paragraph: paragraphs) {
                if(paragraph.text().length() > 0) {
                    row = pSheet.createRow(rowid2++);
                    cell = row.createCell(0);
                    cell.setCellValue((String) "p");
                    cell = row.createCell(1);
                    cell.setCellValue((String) paragraph.text());
                }
            }
            row = pSheet.createRow(rowid2++);
            cell = row.createCell(0);
            cell.setCellValue((String) " ");
            cell = row.createCell(1);
            cell.setCellValue((String) " ");
        }

        try (FileOutputStream outputStream = new FileOutputStream("./fPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fPTags");
        }

        if(!nexturl.isEmpty()) {
            focusedCrawlerUtil(nexturl, depth+1, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2, count);
        }
    }

    public static void focusedCrawler() {
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        XSSFWorkbook fURLs = new XSSFWorkbook();
        XSSFSheet urlSheet = fURLs.createSheet("sheet");
        XSSFWorkbook fPTags = new XSSFWorkbook();
        XSSFSheet pSheet = fPTags.createSheet("sheet");

        int rowid1 = 0;
        int rowid2 = 0;

        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "Text");
        cell = row.createCell(1);
        cell.setCellValue((String) "URL");

        try (FileOutputStream outputStream = new FileOutputStream("./fURLs.xlsx")) {
            fURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fURLs");
        }

        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "Tag");
        cell = row.createCell(1);
        cell.setCellValue((String) "Content");

        try (FileOutputStream outputStream = new FileOutputStream("./fPTags.xlsx")) {
            fPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: fPTags");
        }
        focusedCrawlerUtil(url, 0, fURLs, fPTags, urlSheet, pSheet, rowid1, rowid2, 1);
    }

    public static void downloadableCrawlerUtil(String url, int depth, XSSFWorkbook downloadableURLs, XSSFSheet urlSheet, int rowid)
    {
        if(depth == 600) {
            return;
        }

        System.out.println(url);
        Elements links = new Elements();

        try {
            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            links = doc.select("a[href]");

        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        for(Element link: links)
        {
            list.add(link.absUrl("href"));
            if( link.absUrl("href").toLowerCase().contains(".pdf")  ||
                    link.absUrl("href").toLowerCase().contains(".docx") ||
                    link.absUrl("href").toLowerCase().contains(".xlsx") ||
                    link.absUrl("href").toLowerCase().contains(".doc")  ||
                    link.absUrl("href").toLowerCase().contains(".jpeg") ||
                    link.absUrl("href").toLowerCase().contains(".jpg")) {

                XSSFRow row = urlSheet.createRow(rowid++);
                Cell cell = row.createCell(0);
                cell.setCellValue((String) link.absUrl("href"));
            }

        }

        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty()
                    && !set.contains(link)
                    && !link.contains("#")
                    && !link.equals("https://alumni.pec.ac.in/")
                    && !link.equals("http://alumni.pec.ac.in/")
                    && !link.equals(url)
                    && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))
                    && !link.toLowerCase().contains(".docx")
                    && !link.toLowerCase().contains(".pdf")
                    && !link.toLowerCase().contains(".jpg")
                    && !link.toLowerCase().contains(".doc")
                    && !link.toLowerCase().contains(".xlsx")
                    && !link.toLowerCase().contains(".jpg")) {
                nexturl = link;
                break;
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("./downloadableURLs.xlsx")) {
            downloadableURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: downloadableURLs");
        }

        if(!nexturl.isEmpty())
        {
            downloadableCrawlerUtil(nexturl, depth+1, downloadableURLs, urlSheet, rowid);
        }
    }

    public static void downloadableCrawler()
    {
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();
        XSSFWorkbook downloadableURLs = new XSSFWorkbook();
        XSSFSheet urlSheet = downloadableURLs.createSheet("sheet");
        int rowid = 0;
        XSSFRow row = urlSheet.createRow(rowid++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "PDFLINKS");

        try (FileOutputStream outputStream = new FileOutputStream("./downloadableURLs.xlsx")) {
            downloadableURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: downloadableURLs");
        }

        downloadableCrawlerUtil(url, 0, downloadableURLs, urlSheet, rowid);
    }

    public static void simpleCrawlerUtil(String url, int depth, XSSFWorkbook allURLs, XSSFWorkbook allPTags, XSSFSheet urlSheet, XSSFSheet pSheet, int rowid1, int rowid2)
    {
        if(depth == 50)
        {
            return;
        }
        System.out.println(url);

        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String)"For Url: "+(depth + 1));
        cell = row.createCell(1);
        cell.setCellValue((String) url);

        Elements paragraphs = new Elements();
        Elements links = new Elements();

        try {
            set.add(url);
            Document doc = Jsoup.connect(url).get();
            Element body = doc.body();
            paragraphs = body.getElementsByTag("p");
            links = doc.select("a[href]");
        } catch (Exception e) {
            System.out.println("Something Went Wrong in the url: " + url);
        }

        for (Element link: links) {

            if(link.text().isEmpty() || link.absUrl("href").isEmpty()) {
                continue;
            }
            list.add(link.absUrl("href"));
            row = urlSheet.createRow(rowid1++);
            cell = row.createCell(0);
            cell.setCellValue((String) link.text());
            cell = row.createCell(1);
            cell.setCellValue((String) link.absUrl("href"));
        }

        String nexturl = "";
        for(String link: list) {
            if( nexturl.isEmpty()
                && !set.contains(link)
                && !link.contains("#")
                && !link.equals("https://alumni.pec.ac.in/")
                && !link.equals("http://alumni.pec.ac.in/")
                && !link.equals(url)
                && (link.contains("https://pec.ac.in") || link.contains("http://pec.ac.in"))) {
                nexturl = link;
            }
        }

        row = urlSheet.createRow(rowid1++);
        cell = row.createCell(0);
        cell.setCellValue((String) " ");
        cell = row.createCell(1);
        cell.setCellValue((String) " ");

        try (FileOutputStream outputStream = new FileOutputStream("./allURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allURLs");
        }

        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "For Url: " + (depth + 1));
        cell = row.createCell(1);
        cell.setCellValue((String) url);

        for(Element paragraph: paragraphs) {
            if(paragraph.text().length() > 0) {
                row = pSheet.createRow(rowid2++);
                cell = row.createCell(0);
                cell.setCellValue((String) "p");
                cell = row.createCell(1);
                cell.setCellValue((String) paragraph.text());
            }
        }
        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) " ");
        cell = row.createCell(1);
        cell.setCellValue((String) " ");

        try (FileOutputStream outputStream = new FileOutputStream("./allPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allPTags");
        }
        if(!nexturl.isEmpty()) {
            simpleCrawlerUtil(nexturl, depth+1, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2);
        }
    }

    public static void simpleCrawler()
    {
        System.out.print("Enter Website Url: ");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();

        XSSFWorkbook allURLs = new XSSFWorkbook();
        XSSFSheet urlSheet = allURLs.createSheet("sheet");
        XSSFWorkbook allPTags = new XSSFWorkbook();
        XSSFSheet pSheet = allPTags.createSheet("sheet");

        int rowid1 = 0;
        int rowid2 = 0;

        XSSFRow row = urlSheet.createRow(rowid1++);
        Cell cell = row.createCell(0);
        cell.setCellValue((String) "Text");
        cell = row.createCell(1);
        cell.setCellValue((String) "URL");

        try (FileOutputStream outputStream = new FileOutputStream("./allURLs.xlsx")) {
            allURLs.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allURLs");
        }

        row = pSheet.createRow(rowid2++);
        cell = row.createCell(0);
        cell.setCellValue((String) "Tag");
        cell = row.createCell(1);
        cell.setCellValue((String) "Content");

        try (FileOutputStream outputStream = new FileOutputStream("./allPTags.xlsx")) {
            allPTags.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Something Went Wrong in the writing to file: allPTags");
        }
        simpleCrawlerUtil(url, 0, allURLs, allPTags, urlSheet, pSheet, rowid1, rowid2);
    }

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("MENU");
            System.out.println("\n1. Focused Crawler");
            System.out.println("\n2. Downloadable Crawler");
            System.out.println("\n3. Simple Crawler");
            System.out.print("Enter Option: ");
            Scanner sc = new Scanner(System.in);
            int op = Integer.parseInt(sc.nextLine());

            if(op == 1) focusedCrawler();

            else if(op == 2) downloadableCrawler();

            else if(op == 3) simpleCrawler();

            else break;
        }
        System.out.print("Completed All Processes");
    }
}