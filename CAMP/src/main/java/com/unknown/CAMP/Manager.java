package com.unknown.CAMP;

import java.util.ArrayList;

public class Manager {

	private ArrayList<Camper> fullRoster;
	private ArrayList<ArrayList<Camper>> cabinRoster = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> femaleChunks = new ArrayList<ArrayList<Camper>>(0);
	private ArrayList<ArrayList<Camper>> maleChunks = new ArrayList<ArrayList<Camper>>(0);
	private int maleCabins, femaleCabins, totalCampers, cabinMax;

	public Manager(ArrayList<Camper> a) {
		fullRoster = a;
		totalCampers = fullRoster.size();
		maleCabins = 4;
		femaleCabins = 4;
		cabinMax = 13;
	}

	public Manager(ArrayList<Camper> a, int mcabins, int fcabins) {
		fullRoster = a;
		maleCabins = mcabins;
		femaleCabins = fcabins;
		totalCampers = fullRoster.size();
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
			System.out.println(maleChunks);
			System.out.println(femaleChunks);
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
			chunkMaker();
		} else if (temp.get(0).getGender() == 2) {
			femaleChunks.add(temp);
			// runs it again
			chunkMaker();
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
				temp.addAll(cabinCreator(tempChunks));
			} else {

			}
		}

	}
	
	public ArrayList<ArrayList<Camper>> cabinCreator(ArrayList<ArrayList<Camper>> chunks){
		ArrayList<ArrayList<Camper>> temp = new ArrayList<ArrayList<Camper>>(0);
		int count = 0;
		int tries = 0;
		for(int a = 0;a<maleCabins;a++) {
			for(int b = 0;b<cabinMax;b++) {
				
			}
		}
		
		return temp;
	}

	public static void main(String[] args) {
		// Creates an ArrayList of campers.
		// It will be a for loop soon, but i'm still unsure on the input.
		ArrayList<Camper> campers = new ArrayList<Camper>(0);

		campers.add(new Camper("b", "a", 1, 8));
		campers.add(new Camper("a", "c", 1, 8));
		campers.add(new Camper("c", "a", 1, 8));
		campers.add(new Camper("d", "s", 1, 8));
		campers.add(new Camper("e", "f", 1, 8));
		campers.add(new Camper("g", "h", 1, 8));
		campers.add(new Camper("h", "b", 1, 8));
		campers.add(new Camper("j", "c", 1, 8));
		campers.add(new Camper("k", "a", 1, 8));
		campers.add(new Camper("l", "c", 1, 8));

		Manager addie = new Manager(campers);
		addie.chunkMaker();
	}

}
