import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Exercise {
  private static final NumberFormat FORMATTER = new DecimalFormat("#0.0");

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    int monthlyDistance = getDistanceFromUser(scanner);
    int insuranceDiscountPercent = getInsuranceDiscountFromUser(scanner);

    // Read the Excel file into a List of LeasingCar Objects
    List<LeasingCar> leasingCarList = readLeasingCars("LeasingCars.xls");

    // Calculate the total cost for each car (and find the least expensive option)
    calculateCosts(leasingCarList, monthlyDistance, insuranceDiscountPercent);
    LeasingCar bestOffer = findBestOffer(leasingCarList);

    Formatter formatter = new Formatter();

    System.out.println("\nThe " + bestOffer.getBrand() + " " + bestOffer.getModel()
        + " is the best offer with a total cost of "
        + formatter.format("%.2f", bestOffer.getMonthlyCost()));
  }

  private static List<LeasingCar> readLeasingCars(String fileName) throws IOException {

    FileInputStream file = new FileInputStream(new File("src/main/resources/" + fileName));
    List<LeasingCar> cars = new ArrayList<>();

    Workbook workbook = new HSSFWorkbook(file);
    Sheet sheet = workbook.getSheetAt(0);

    for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
      Row row = sheet.getRow(rowIndex);
      if (row == null) continue;

      LeasingCar car = new LeasingCar();
      car.setBrand(row.getCell(0).getStringCellValue());
      car.setModel(row.getCell(1).getStringCellValue());
      car.setMonthlyLeasingAmount(row.getCell(2).getNumericCellValue());
      car.setYearlyInsuranceCost(row.getCell(3).getNumericCellValue());
      car.setFuelConsumptionPer100Km(row.getCell(4).getNumericCellValue());

      cars.add(car);
    }
    return cars;
  }

  private static void calculateCosts(List<LeasingCar> leasingCarList, int monthlyDistance,
      int insuranceDiscountPercent) {
    for (LeasingCar car : leasingCarList) {
      car.calculateMonthlyCost(monthlyDistance, insuranceDiscountPercent);
      System.out.println("Monthly cost for the " + car.getBrand() + " " + car.getModel() + " is " + FORMATTER.format(car.getMonthlyCost()));
    }
  }

  private static LeasingCar findBestOffer(List<LeasingCar> leasingCarList) {
    LeasingCar bestOffer = leasingCarList.getFirst();

    for (LeasingCar car : leasingCarList) {
      if (car.getMonthlyCost() < bestOffer.getMonthlyCost()) {
        bestOffer = car;
      }
    }
    return bestOffer;
  }

  private static int getDistanceFromUser(Scanner scanner) {
    System.out.println("Please enter the amount of kilometers you drive monthly (no decimals):");
    return scanner.nextInt();
  }

  private static int getInsuranceDiscountFromUser(Scanner scanner) {
    System.out.println("Please enter the percent-discount for your insurance plan:");
    return scanner.nextInt();
  }


}
