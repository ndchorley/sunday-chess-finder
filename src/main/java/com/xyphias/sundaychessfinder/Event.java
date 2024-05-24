package com.xyphias.sundaychessfinder;

import java.net.URL;
import java.time.LocalDate;

public record Event(String name, LocalDate date, URL url, boolean isJunior) {}
