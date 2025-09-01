import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class User {
    String name;
    String email;
    String age;
    String location;
    int currentStreak;
    int totalPoints;
    List<Submission> submissions;
    List<AITip> aiTips;
}

class Submission {
    double totalEmissionScore;
    double transportationScore;
    double energyScore;
    double waterScore;
    double dietScore;
    double foodWasteScore;
    double shoppingScore;
    double wasteScore;
    double electronicsScore;
    double travelScore;
    double applianceScore;
    String impactCategory;
}

class AITip {
    String title;
    String category;
    String impact;
    String description;
    String reasoning;
}

public class PDFReportGenerator {

    public static byte[] generatePDFReport(User user, String reportType) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf, PageSize.A4);

        float pageWidth = pdf.getDefaultPageSize().getWidth();
        float pageHeight = pdf.getDefaultPageSize().getHeight();

        // Fonts
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont normal = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        // Colors
        Color primaryColor = new DeviceRgb(34, 139, 34);
        Color secondaryColor = new DeviceRgb(100, 100, 100);
        Color accentColor = new DeviceRgb(0, 123, 255);

        // Header
        Paragraph header = new Paragraph("🌱 GIKI ZERO")
                .setFont(bold)
                .setFontSize(32)
                .setFontColor(Color.WHITE)
                .setMarginTop(10)
                .setMarginBottom(0);
        doc.add(header);

        Paragraph subHeader = new Paragraph("Carbon Footprint Report - " + capitalize(reportType))
                .setFont(normal)
                .setFontSize(14)
                .setFontColor(Color.WHITE)
                .setMarginTop(0)
                .setMarginBottom(20);
        doc.add(subHeader);

        // Date metadata
        String dateStr = new SimpleDateFormat("MMMM dd, yyyy, HH:mm").format(new Date());
        doc.add(new Paragraph("Generated on: " + dateStr)
                .setFont(normal)
                .setFontColor(secondaryColor)
                .setFontSize(12)
        );

        doc.add(new Paragraph("\n"));

        // Personal Info Section
        doc.add(new Paragraph("👤 Personal Information")
                .setFont(bold)
                .setFontSize(18)
                .setFontColor(primaryColor)
        );

        doc.add(new Paragraph("Name: " + (user.name != null ? user.name : "Not provided")).setFont(normal).setFontSize(11));
        doc.add(new Paragraph("Email: " + user.email).setFont(normal).setFontSize(11));
        doc.add(new Paragraph("Age: " + (user.age != null ? user.age : "Not provided")).setFont(normal).setFontSize(11));
        doc.add(new Paragraph("Location: " + (user.location != null ? user.location : "Not provided")).setFont(normal).setFontSize(11));

        doc.add(new Paragraph("\n"));

        // Performance Summary
        if (user.submissions != null && !user.submissions.isEmpty()) {
            Submission latest = user.submissions.get(0);
            double averageScore = user.submissions.stream().mapToDouble(s -> s.totalEmissionScore).average().orElse(0);

            doc.add(new Paragraph("📊 Performance Summary")
                    .setFont(bold)
                    .setFontSize(18)
                    .setFontColor(accentColor)
            );

            doc.add(new Paragraph("Latest Score: " + String.format("%.1f kg CO₂", latest.totalEmissionScore)).setFont(normal).setFontSize(12));
            doc.add(new Paragraph("Average Score: " + String.format("%.1f kg CO₂", averageScore)).setFont(normal).setFontSize(12));
            doc.add(new Paragraph("Impact Category: " + latest.impactCategory).setFont(normal).setFontSize(12));
            doc.add(new Paragraph("Total Submissions: " + user.submissions.size()).setFont(normal).setFontSize(12));
            doc.add(new Paragraph("Current Streak: " + user.currentStreak + " days").setFont(normal).setFontSize(12));
            doc.add(new Paragraph("Total Points: " + user.totalPoints).setFont(normal).setFontSize(12));

            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("🏷️ Category Breakdown").setFont(bold).setFontSize(18).setFontColor(primaryColor));
            doc.add(new Paragraph("\n"));

            // Example: Just showing Transportation
            doc.add(new Paragraph("🚗 Transportation: " + latest.transportationScore + " kg CO₂").setFont(normal).setFontSize(11));
        }

        // AI Tips Section
        if (user.aiTips != null && !user.aiTips.isEmpty()) {
            doc.add(new Paragraph("\n💡 AI-Powered Sustainability Tips").setFont(bold).setFontSize(18).setFontColor(primaryColor));

            int count = 1;
            for (AITip tip : user.aiTips) {
                doc.add(new Paragraph(count + ". " + tip.title).setFont(bold).setFontSize(13));
                doc.add(new Paragraph(tip.category + " • " + tip.impact + " Impact").setFont(normal).setFontSize(9).setFontColor(secondaryColor));
                doc.add(new Paragraph(tip.description).setFont(normal).setFontSize(10));
                doc.add(new Paragraph("💭 " + tip.reasoning).setFont(normal).setFontSize(10).setFontColor(secondaryColor));
                doc.add(new Paragraph("\n"));
                count++;
            }
        }

        // Footer
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler() {
            @Override
            public void handleEvent(Event event) {
                int pageNumber = pdf.getPageNumber(((PdfDocumentEvent) event).getPage());
                float y = pageHeight - 20;
                Document d = new Document(pdf);
                Paragraph footer = new Paragraph("Generated by Giki Zero - Your Carbon Footprint Companion • Page " + pageNumber)
                        .setFont(normal)
                        .setFontSize(9)
                        .setFontColor(secondaryColor)
                        .setTextAlignment(TextAlignment.CENTER);
                d.showTextAligned(footer, pageWidth / 2, y, pageNumber, TextAlignment.CENTER, 0);
            }
        });

        doc.close();
        return baos.toByteArray();
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
