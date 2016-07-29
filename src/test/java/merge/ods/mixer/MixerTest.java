package merge.ods.mixer;

public class MixerTest {

//	private File original;
//	private File firstChange;
//	private File secondChange;
//	private Mixer mixer;

//	@Before
//	public void setup() throws FileNotFoundException, IOException {
////		createOriginal();
////		createFirstChange();
////		createSecondChange();
//
//		MixerStrategy strategy = new SimpleMixerStrategy(SpreadSheet.createFromFile(original), SpreadSheet.createFromFile(firstChange),SpreadSheet.createFromFile(secondChange));
//		mixer = new Mixer(strategy);
//	}

//	private void createOriginal() throws FileNotFoundException, IOException {
//		original = createBaseSpreadSheetFile(MixerConfiguration.ORIGINAL_DEFAULT_FILE_NAME);
//	}
//
//	private void createFirstChange() throws FileNotFoundException, IOException {
//		firstChange = createBaseSpreadSheetFile(MixerConfiguration.FIRST_DEFAULT_CHANGED_FILE_NAME);
//	}
//
//	private void createSecondChange() throws FileNotFoundException, IOException {
//		secondChange = createBaseSpreadSheetFile(MixerConfiguration.SECOND_DEFAULT_CHANGED_FILE_NAME);
//	}

//	private File createBaseSpreadSheetFile(String name)
//			throws FileNotFoundException, IOException {
//		final Object[][] data = new Object[1][4];
//		data[0] = new Object[] { "Sergio", "Bogado", "26", "0" };
//		String[] columns = new String[] { "Nopmbre", "Apellido", "Edad",
//				"Hijos" };
//		TableModel model = new DefaultTableModel(data, columns);
//		final File file = new File(name);
//		SpreadSheet spreadSheet = SpreadSheet.createEmpty(model);
//		spreadSheet.getFirstSheet().setName("Activos");
//		spreadSheet.saveAs(file);
//		return file;
//	}

//	// @Test
//	public void testMixCellOnlyFirstHaveAChange() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(firstChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Sebastian");
//		sheet1.getSpreadSheet().saveAs(firstChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixCell(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 2, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Sebastian", resultSheet.getCellAt("A2").getValue());
//	}
//
//	// @Test
//	public void testMixCellOnlySecondHaveAChange() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(secondChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Juanito");
//		sheet1.getSpreadSheet().saveAs(secondChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixCell(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 2, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Juanito", resultSheet.getCellAt("A2").getValue());
//	}
//
//	// @Test
//	public void testMixCellBothHaveAChanged() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(firstChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Sebastian");
//		sheet1.getSpreadSheet().saveAs(firstChange);
//
//		final Sheet sheet2 = SpreadSheet.createFromFile(secondChange).getSheet(
//				"Activos");
//		sheet2.getCellAt("A2").setValue("Junior");
//		sheet2.getSpreadSheet().saveAs(secondChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixCell(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 2, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Sebastian,Junior", resultSheet.getCellAt("A2")
//				.getValue());
//		Assert.assertEquals(Color.RED, resultSheet.getCellAt("A2").getStyle()
//				.getBackgroundColor(resultSheet.getCellAt("A2")));
//	}
//
//	@Test
//	public void testMixRowOnlyFirstHaveAChange() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(firstChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Sebastian");
//		sheet1.getCellAt("B2").setValue("Banega");
//		sheet1.getCellAt("C2").setValue("32");
//		sheet1.getCellAt("D2").setValue("1");
//		sheet1.getSpreadSheet().saveAs(firstChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixRow(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Sebastian", resultSheet.getCellAt("A2").getValue());
//		Assert.assertEquals("Banega", resultSheet.getCellAt("B2").getValue());
//		Assert.assertEquals("32", resultSheet.getCellAt("C2").getValue());
//		Assert.assertEquals("1", resultSheet.getCellAt("D2").getValue());
//	}
//
//	@Test
//	public void testMixRowOnlySecondHaveAChange() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(secondChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Mely");
//		sheet1.getCellAt("B2").setValue("Branfman");
//		sheet1.getCellAt("C2").setValue("31");
//		sheet1.getCellAt("D2").setValue("0");
//		sheet1.getSpreadSheet().saveAs(secondChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixRow(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Mely", resultSheet.getCellAt("A2").getValue());
//		Assert.assertEquals("Branfman", resultSheet.getCellAt("B2").getValue());
//		Assert.assertEquals("31", resultSheet.getCellAt("C2").getValue());
//		Assert.assertEquals("0", resultSheet.getCellAt("D2").getValue());
//	}
//
//	@Test
//	public void testMixRowBothHaveAChange() throws IOException {
//		final Sheet sheet1 = SpreadSheet.createFromFile(firstChange).getSheet(
//				"Activos");
//		sheet1.getCellAt("A2").setValue("Sebastian");
//		sheet1.getCellAt("B2").setValue("Banega");
//		sheet1.getCellAt("C2").setValue("32");
//		sheet1.getCellAt("D2").setValue("1");
//		sheet1.getSpreadSheet().saveAs(firstChange);
//
//		final Sheet sheet2 = SpreadSheet.createFromFile(secondChange).getSheet(
//				"Activos");
//		sheet2.getCellAt("A2").setValue("Mely");
//		sheet2.getCellAt("B2").setValue("Branfman");
//		sheet2.getCellAt("C2").setValue("31");
//		sheet2.getCellAt("D2").setValue("0");
//		sheet2.getSpreadSheet().saveAs(secondChange);
//
//		SpreadSheet originalSpreatSheet = SpreadSheet.createFromFile(original);
//		SpreadSheet firstChangeSpreatSheet = SpreadSheet
//				.createFromFile(firstChange);
//		SpreadSheet secondChangeSpreatSheet = SpreadSheet
//				.createFromFile(secondChange);
//
//		SpreadSheet result = mixer.mixRow(originalSpreatSheet,
//				firstChangeSpreatSheet, secondChangeSpreatSheet, 1);
//		final Sheet resultSheet = result.getSheet("Activos");
//
//		Assert.assertEquals("Sebastian,Mely", resultSheet.getCellAt("A2")
//				.getValue());
//		Assert.assertEquals("Banega,Branfman", resultSheet.getCellAt("B2")
//				.getValue());
//		Assert.assertEquals("32,31", resultSheet.getCellAt("C2").getValue());
//		Assert.assertEquals("1", resultSheet.getCellAt("D2").getValue());
//
//		Assert.assertEquals(Color.RED, resultSheet.getCellAt("A2").getStyle()
//				.getBackgroundColor(resultSheet.getCellAt("A2")));
//		Assert.assertEquals(Color.RED, resultSheet.getCellAt("B2").getStyle()
//				.getBackgroundColor(resultSheet.getCellAt("B2")));
//		Assert.assertEquals(Color.RED, resultSheet.getCellAt("C2").getStyle()
//				.getBackgroundColor(resultSheet.getCellAt("C2")));
//
//	}
}
