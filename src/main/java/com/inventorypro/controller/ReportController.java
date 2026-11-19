package com.inventorypro.controller;
import com.inventorypro.repository.*;import org.apache.pdfbox.pdmodel.*;import org.apache.pdfbox.pdmodel.font.Standard14Fonts;import org.apache.pdfbox.pdmodel.PDPageContentStream;import org.springframework.http.*;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.GetMapping;import java.io.*;
@Controller public class ReportController {
 private final ProductRepository products;private final PurchaseRequestRepository requests;
 public ReportController(ProductRepository p,PurchaseRequestRepository r){products=p;requests=r;}
 @GetMapping("/reports/inventory.pdf") public ResponseEntity<byte[]> pdf() throws IOException {
  try(PDDocument doc=new PDDocument();ByteArrayOutputStream out=new ByteArrayOutputStream()){
   PDPage page=new PDPage();doc.addPage(page);try(PDPageContentStream cs=new PDPageContentStream(doc,page)){
    cs.beginText();cs.setFont(new org.apache.pdfbox.pdmodel.font.PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD),18);cs.newLineAtOffset(50,750);cs.showText("InventoryPro - Inventory Report");cs.setFont(new org.apache.pdfbox.pdmodel.font.PDType1Font(Standard14Fonts.FontName.HELVETICA),11);cs.newLineAtOffset(0,-30);cs.showText("Products: "+products.count()+" | Purchase requests: "+requests.count());cs.newLineAtOffset(0,-28);
    for(var p:products.findAll()){cs.showText(p.getName()+" | SKU: "+p.getSku()+" | Qty: "+p.getQuantity()+" | Price: "+p.getUnitPrice());cs.newLineAtOffset(0,-18);}
    cs.endText();
   }doc.save(out);return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=inventory-report.pdf").body(out.toByteArray());
  }
 }
}
