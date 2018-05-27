package com.unknown.CAMP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class Manager {

	private ArrayList<Camper> fullRoster;
	private ArrayList<ArrayList<Camper>> cabinRoster = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> overflowCabin = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> femaleChunks = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> maleChunks = new ArrayList<ArrayList<Camper>>(0);
	private int maleCabins, femaleCabins, totalCampers, cabinMax,smallGroupNumber;

	public Manager(ArrayList<Camper> a) {
		fullRoster = a;
		totalCampers = fullRoster.size();
		maleCabins = 4;
		femaleCabins = 4;
		cabinMax = 11;
		smallGroupNumber = 8;
	}
	
	public Manager(ArrayList<Camper> a, int cm,int sg, int mc, int fc) {
		fullRoster = a;
		//totalCampers = fullRoster.size();
		maleCabins = mc;
		femaleCabins = fc;
		cabinMax = cm;
		smallGroupNumber = sg;
		
	}
	
	public Manager() {
		maleCabins = 4;
		femaleCabins = 4;
		cabinMax = 10;
		smallGroupNumber = 8;
	}
	

	public Manager(ArrayList<Camper> a, int mcabins, int fcabins) {
		fullRoster = a;
		maleCabins = mcabins;
		femaleCabins = fcabins;
		totalCampers = fullRoster.size();
	}

	public void parseFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        if(line == null) {
		        	break;
		        }
		        System.out.println(line);
		        
		    }
		    String everything = sb.toString();
		} finally {
		    br.close();
		}
	}
	public int chunkMaker() {
		/*
		 * woah, that is some spaghetti code! Let's talk through it Summary: Creates a
		 * temporary array,and assigns one person inside.Then it searches for people who
		 * requested the one person. Then it trys to find the one persons request, then
		 * is continues down the array until it finds the end, this is a chunk. It
		 * assigns the chunk to a gender array.
		 */
		int count = 0;
		// creates a temp array
		ArrayList<Camper> temp = new ArrayList<Camper>(0);
		// checks to see if everyone has been assigned to a chunk
		if (fullRoster.size() < 1) {
			System.out.println("Male Chunks: " + maleChunks);
			System.out.println("Female Chunks: "+femaleChunks);
			return 1;
		}
		temp.add(fullRoster.remove(0));
		// takes a single person, and finds their request,and checks to see if anyone
		// wants to be with them.
		while (count < temp.size()) {
			for (int a = 0; a < fullRoster.size(); a++) {
				// looking for people who requested the person
				if (fullRoster.get(a).getRequest().equals(temp.get(count).getName())
						&& fullRoster.get(a).getGender() == temp.get(count).getGender()) {
					temp.add(fullRoster.remove(a));
					a--;
				}
			}
			for (int a = 0; a < fullRoster.size(); a++) {
				// checks to see if the current persons request is available
				if (fullRoster.get(a).getName().equals(temp.get(count).getRequest())
						&& fullRoster.get(a).getGender() == temp.get(count).getGender()) {
					temp.add(fullRoster.remove(a));
					a--;
				}
			}
			count++;
		}
		// now it assigns the chunk to the correct gender array
		if (temp.get(0).getGender() == 1) {
			maleChunks.add(temp);
			// runs it again
			return chunkMaker();
		} else if (temp.get(0).getGender() == 2) {
			System.out.println("Female");
			femaleChunks.add(temp);
			// runs it again
			return chunkMaker();
		}

		// this should never be returned. but who knows
		System.out.println("Woah, this is an error in the chunk maker.");
		return -1;

	}

	public void cabinAssigner(int mode) {
		// two modes mode 1 will fill cabins before moving on, mode 2 will try and even
		// fill
		ArrayList<ArrayList<Camper>> temp = new ArrayList<ArrayList<Camper>>(0);
		ArrayList<ArrayList<Camper>> tempChunks = maleChunks;
		
		
	
		
		for (int a = 0; a < 2; a++) {
			if (mode == 1) {
				//Not operational
				//temp.addAll(cabinCreator(tempChunks));
			} else {
				Collections.sort(tempChunks, new Comparator<ArrayList>(){
				    public int compare(ArrayList a1, ArrayList a2) {
				        if(a2.size() - a1.size() == 0) {
				        	int random = ThreadLocalRandom.current().nextInt(-totalCampers, totalCampers + 1);
				        	return random;
				        	
				        }
				    	return a2.size() - a1.size(); // assumes you want biggest to smallest
				    }
				});
				System.out.println("Sorted Chunk: " + (a+1) + "" + tempChunks);
				System.out.println("Starting Cabin Creation");
				temp.addAll(cabinCreator(tempChunks));
				System.out.println("Cabins Created: " + (a+1));
				
				
				
				
				
			}
			tempChunks = femaleChunks;
		}
		cabinRoster.addAll(temp);
		

	}
	
	public ArrayList<ArrayList<Camper>> cabinCreator(ArrayList<ArrayList<Camper>> chunks){
		ArrayList<ArrayList<Camper>> temp = new ArrayList<ArrayList<Camper>>(0);
		ArrayList<ArrayList<Camper>> tempOverflow = new ArrayList<ArrayList<Camper>>(0);
		if(chunks.isEmpty()) {
			return temp;
		}
		int tempGender = chunks.get(0).get(0).getGender();
		int giveUp = 0;
		int cabinCount = 0;
		int ascending = 1;
		
		if(tempGender == 1) {
			int count = maleCabins - 1;
			for(int a = 0;a<maleCabins;a++) {
				temp.add(chunks.remove(0));
				System.out.println("Creating initial cabins: " + a);
			}
			while(!chunks.isEmpty()) {
				System.out.println("Is empty check");
				cabinCount = temp.get(count).size();
				
				if(cabinCount + chunks.get(0).size() >= cabinMax) {
					tempOverflow.add(chunks.remove(0));
					System.out.println("Thow Cup Runneth Over.");
				}else {
					temp.get(count).addAll(chunks.remove(0));
				}
				
				if(count == maleCabins-1 && count == 0) {
					ascending = -1;
				}else if(count == maleCabins-1) {
					ascending = 1;
				}else if(count == 0) {
					ascending = 2;
				}
				if(ascending == 2) {
					count++;
				}else if(ascending == 1){
					count--;
				}
			}
			System.out.println(tempOverflow);
			while(!tempOverflow.isEmpty()) {
				for(int b = 0;b<temp.size();b++) {
					if(tempOverflow.isEmpty()) {
						break;
					}
				System.out.println(tempOverflow.get(0).get(0).getName());
					cabinCount = temp.get(b).size();
					if(cabinCount + tempOverflow.get(0).size() <= cabinMax) {
						System.out.println(cabinCount + tempOverflow.get(0).size() + tempOverflow.get(0).get(0).getName() + b);
						temp.get(b).addAll(tempOverflow.remove(0));
						b = 0;
					}
				}
				if(!tempOverflow.isEmpty()) {
					overflowCabin.add(tempOverflow.remove(0));
				}
				
			}
			
		}else {
			int count = femaleCabins - 1;
			for(int a = 0;a<femaleCabins;a++) {
				temp.add(chunks.remove(0));
				System.out.println("Creating initial cabins: " + a);
			}
			while(!chunks.isEmpty()) {
				cabinCount = temp.get(count).size();
				
				if(cabinCount + chunks.get(0).size() >= cabinMax) {
					tempOverflow.add(chunks.remove(0));
					System.out.println("Thow Cup Runneth Over.");
				}else {
					temp.get(count).addAll(chunks.remove(0));
				}
				
				if(count == femaleCabins-1 && count == 0) {
					ascending = -1;
				}else if(count == femaleCabins-1) {
					ascending = 1;
				}else if(count == 0) {
					ascending = 2;
				}
				if(ascending == 2) {
					count++;
				}else if(ascending == 1){
					count--;
				}
			}
			System.out.println(tempOverflow);
			while(!tempOverflow.isEmpty()) {
				for(int b = 0;b<temp.size();b++) {
					if(tempOverflow.isEmpty()) {
						break;
					}
				System.out.println(tempOverflow.get(0).get(0).getName());
					cabinCount = temp.get(b).size();
					if(cabinCount + tempOverflow.get(0).size() <= cabinMax) {
						System.out.println(cabinCount + tempOverflow.get(0).size() + tempOverflow.get(0).get(0).getName() + b);
						temp.get(b).addAll(tempOverflow.remove(0));
						b = 0;
					}
				}
				if(!tempOverflow.isEmpty()) {
					overflowCabin.add(tempOverflow.remove(0));
				}
				
			}
		}
		
		
		return temp;
	}

	public void setCamperInfo(String fName) {
		Writer w = new Writer();
		int count = 1;
		for(int a = 0;a<cabinRoster.size();a++) {
			for(int b = 0;b<cabinRoster.get(a).size();b++) {
				if(smallGroupNumber < count) {
					count = 1;
				}
		
				cabinRoster.get(a).get(b).setCabin(a+1);
				cabinRoster.get(a).get(b).setGroup(count);
				w.writeCamper(cabinRoster.get(a).get(b));
				count++;
			}
			
		}
		for(int a = 0;a<overflowCabin.size();a++) {
			for(int b = 0;b<overflowCabin.get(a).size();b++) {
				if(smallGroupNumber < count) {
					count = 1;
				}
				overflowCabin.get(a).get(b).setCabin(-1);
				overflowCabin.get(a).get(b).setGroup(count);
				w.writeCamper(overflowCabin.get(a).get(b));
				count++;
			}
			
		}
		try {
			w.endSpreadsheet(fName + ".xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fullProgram(String fName) {
		chunkMaker();
		cabinAssigner(2);
		setCamperInfo(fName);
	}
	

	public static void main(String[] args) {
		// Creates an ArrayList of campers.
		// It will be a for loop soon, but i'm still unsure on the input.
		ArrayList<Camper> campers = new ArrayList<Camper>(0);

		
		campers.add(new Camper("Bruce", "Wilson", 2, 8));
		campers.add(new Camper("Lenny", "", 2, 8));
		campers.add(new Camper("Sam", "Tim", 2, 8));
		campers.add(new Camper("Marco", "", 2, 8));
		campers.add(new Camper("Parker", "", 2, 8));
		campers.add(new Camper("Judson", "", 2, 8));
		campers.add(new Camper("Tim", "Lenny", 2, 8));
		campers.add(new Camper("Larry", "", 2, 8));
		campers.add(new Camper("Sammy", "", 2, 8));
		campers.add(new Camper("Wilson", "", 2, 8));
		campers.add(new Camper("Evans", "", 2, 8));
		campers.add(new Camper("Jim", "Wilson", 2, 8));
		campers.add(new Camper("Sage", "", 2, 8));
		campers.add(new Camper("Bailey", "Tim", 2, 8));
		campers.add(new Camper("Arthur", "", 2, 8));
		campers.add(new Camper("Matthew", "", 2, 8));
		campers.add(new Camper("Tiny", "", 2, 8));
		campers.add(new Camper("Conan", "Lenny", 2, 8));
		campers.add(new Camper("Norm", "", 2, 8));
		campers.add(new Camper("Saske", "", 2, 8));
		campers.add(new Camper("Kirito", "", 2, 8));
		campers.add(new Camper("Wade", "", 2, 8));
		campers.add(new Camper("William", "Wilson", 2, 8));
		campers.add(new Camper("John", "", 2, 8));
		campers.add(new Camper("David", "Tim", 2, 8));
		campers.add(new Camper("William", "", 2, 8));
		campers.add(new Camper("Jarod", "", 2, 8));
		campers.add(new Camper("Emil", "", 2, 8));
		campers.add(new Camper("Roderick", "Lenny", 2, 8));
		campers.add(new Camper("Elroy", "", 2, 8));
		campers.add(new Camper("Gil", "", 2, 8));
		campers.add(new Camper("Len", "", 2, 8));
		campers.add(new Camper("Cruz", "", 2, 8));
		campers.add(new Camper("Lamar", "Wilson", 2, 8));
		campers.add(new Camper("Johnson", "", 2, 8));
		campers.add(new Camper("Rubin", "Tim", 2, 8));
		campers.add(new Camper("Korey", "", 2, 8));
		campers.add(new Camper("Dion", "", 2, 8));
		campers.add(new Camper("Cornell", "", 1, 8));
		campers.add(new Camper("Emile", "Lenny", 1, 8));
		campers.add(new Camper("Rocky", "", 1, 8));
		campers.add(new Camper("Bobby", "", 1, 8));
		campers.add(new Camper("Marshall", "", 1, 8));
		campers.add(new Camper("Wade", "", 1, 8));
		
		campers.add(new Camper("Sarah", "Judy", 2, 8));
		campers.add(new Camper("Haley", "Judy", 2, 8));
		campers.add(new Camper("Annie", "", 2, 8));
		campers.add(new Camper("Virginia", "", 2, 8));
		campers.add(new Camper("Caroline", "", 2, 8));
		campers.add(new Camper("Kate", "", 2, 8));
		campers.add(new Camper("Judy", "", 2, 8));
		campers.add(new Camper("Samantha", "", 2, 8));
		campers.add(new Camper("Blaire", "", 2, 8));
		campers.add(new Camper("Gracie", "", 2, 8));
		campers.add(new Camper("Emma", "", 2, 8));
		

		Manager addie = new Manager(campers);
	
		addie.fullProgram("TestFile");
	}

}
