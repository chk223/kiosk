package com.example.kiosk.service;

import com.example.kiosk.domain.MenuItem;

import java.util.Scanner;

public interface PrintService {
    public void printSelectedMenuItem(MenuItem menuItem);
    public void printMenu();
    public void printBurgers(Scanner scanner);
    public void printDrinks(Scanner scanner);
    public void printDesserts(Scanner scanner);
    void lastSentence(String action);
}
