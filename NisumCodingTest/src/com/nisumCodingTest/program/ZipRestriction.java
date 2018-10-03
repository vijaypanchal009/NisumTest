package com.nisumCodingTest.program;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Vijay Panchal
 * This programs is written to minimize the zipcode ranges as much as possible.
 * User can insert no of ranges and then it will be sorted first of all before it identifies the multiple overlapping ranges available.
 * Overlapping ranges will be summarized in single range.
 */

public class ZipRestriction {

	public static void main(String[] args) {

		List<ZipRanges> zipRangesList = new ArrayList<>();
	
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the size of ranges available: ");
		int size = scanner.nextInt();
		
		for (int i = 0; i < size; i++) {
			System.out.print("\n ************ Range No: "+(i+1));
			System.out.print("\n Enter Lower Range : ");
			int lower = scanner.nextInt();
			boolean repeat = false;
			do {
				System.out.print(" \n Enter Upper Range : ");
				int upper = scanner.nextInt();
				if(upper < lower) {
					repeat = true;
					System.out.print("\n Enter bigger value than Lower Range ");
				} else {
					zipRangesList.add(new ZipRanges(lower, upper));
					repeat = false;
				}
			} while(repeat);
		}
		
		/*zipRangesList.add(new ZipRanges(94133,94133));
		zipRangesList.add(new ZipRanges(94200,94299));
		zipRangesList.add(new ZipRanges(94226,94399));
		zipRangesList.add(new ZipRanges(93226,93399));
		zipRangesList.add(new ZipRanges(94346,94369));
		zipRangesList.add(new ZipRanges(94400,94449));
		zipRangesList.add(new ZipRanges(90000,95000));
		zipRangesList.add(new ZipRanges(94133,94179));
		zipRangesList.add(new ZipRanges(94038,94253));
		zipRangesList.add(new ZipRanges(94169,94199));
		zipRangesList.add(new ZipRanges(94450,94475));
		zipRangesList.add(new ZipRanges(94590,94622));
		zipRangesList.add(new ZipRanges(94012,94123));*/
		
		System.out.print("\n Given Input ZipRanges :- " );
		zipRangesList.stream().forEach(System.out::print);
		
		zipRangesList = new ZipRestriction().produceMinNumOfRanges(zipRangesList);
		
		System.out.print(" \n Minimized ZipRanges :- " );
		zipRangesList.stream().forEach(System.out::print);
	}
	
	private List<ZipRanges> produceMinNumOfRanges(List<ZipRanges> zipRangesList) {
		if(zipRangesList == null || zipRangesList.size() == 0) {
			return zipRangesList;
		}
		int rangeSize = zipRangesList.size(); 
		if(rangeSize == 1) { 
			return zipRangesList;
		} else {
			Collections.sort(zipRangesList);
			System.out.print(" \n sorted ZipRanges :- " );
			zipRangesList.stream().forEach(System.out::print);
			
			int i =0;
			List<ZipRanges> newZipRanges = new ArrayList<>();
			
			while(i < zipRangesList.size()) {
				int lower = zipRangesList.get(i).getLower();
				int upper = zipRangesList.get(i).getUpper();
				int j = i+1;
				int ignoreCount = 0;
				while(j < zipRangesList.size()) {
					if(upper >= zipRangesList.get(j).getLower()) {
						if(lower > zipRangesList.get(j).getLower()) {
							lower = zipRangesList.get(j).getLower();
						}
						if(upper < zipRangesList.get(j).getUpper()) {
							upper = zipRangesList.get(j).getUpper();
						}
						ignoreCount++;
					} else {
						break;
					}
					j++;
				}
				ZipRanges temp = new ZipRanges(lower, upper);
				newZipRanges.add(temp);
				i += 1 + ignoreCount;
			}
			return newZipRanges;
		}
	}
}

/*
 * Class : ZipRanges 
 * lower : It is a lower range of the zipcode restriction.
 * upper : It is a upper range of the zipcode restriction.
 * ZipRanges has a lower and upper limit of the zipcode for a restriction. The zipcode in between including both range are restrictions for product delivery. 
 */
class ZipRanges implements Comparable<ZipRanges> {
	
	private Integer lower;
	private Integer upper;
	
	ZipRanges(Integer lower, Integer upper) {
		this.lower = lower;
		this.upper = upper;
	}
	
	public Integer getLower() {
		return lower;
	}
	public void setLower(Integer lower) {
		this.lower = lower;
	}
	
	public Integer getUpper() {
		return upper;
	}
	public void setUpper(Integer upper) {
		this.upper = upper;
	}
	
	@Override
	public String toString() {
		return "["+lower+", "+upper+"]";
	}
	
	@Override
	public int compareTo(ZipRanges obj) {
		if(this == obj || this.lower == obj.lower) {
			return 0;
		} else if(this.lower < obj.lower) {
			return -1;
		} else {
			return 1;
		}
	}
}


