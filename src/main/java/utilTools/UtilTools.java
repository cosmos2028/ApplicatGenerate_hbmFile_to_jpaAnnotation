package utilTools;

import java.time.LocalDate;
import java.time.Period;

public class UtilTools 
{
	public static boolean isNullOrWithSpace(String mot) {
		return mot == null || mot.trim().isEmpty();
	}

	public static String capitalize(String mot) 
	{
		if (!isNullOrWithSpace(mot)) {
			return Character.toUpperCase(mot.charAt(0)) + mot.substring(1).toLowerCase();
		} else {
			return null;
		}
	}
	public static int calculAge(LocalDate ddn)
	{
		LocalDate dateMaintenant = LocalDate.now();
		Period period = Period.between(ddn,dateMaintenant);
		return period.getYears();
	}
	
	public static String upperCase(String mot)
	{
		return mot.toUpperCase();
	}
	public static String motAsString(String mot)
	{
		return "\""+mot+"\"";
	}
	
	public static String getLastNamePath(String mot)
	{
		StringBuilder classeJavaNomAbsolutPath = new StringBuilder(mot);

		String[] classeJavaSimpleNoms = classeJavaNomAbsolutPath.toString().split("\\."); 

		return  classeJavaSimpleNoms.length>1?classeJavaSimpleNoms[classeJavaSimpleNoms.length-1]:mot;
		
	}
	public static String getLastNamePathFile(String path)
	{
		StringBuilder fileNomRelatifPath = new StringBuilder(path);

		String[] fileSimpleNoms = fileNomRelatifPath.toString().split("\\"); 

		return  fileSimpleNoms.length>1 ?fileSimpleNoms[fileSimpleNoms.length-2]:null;
		
	}
	public static String addExtensionFileJavaToFileName(String fileName)
	{
		

		return  fileName+".java";
		
	}
	
}
