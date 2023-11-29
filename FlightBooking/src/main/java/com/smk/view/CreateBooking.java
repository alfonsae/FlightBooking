package com.smk.view;

import FlightBooking.src.main.java.com.smk.MainView;
import FlightBooking.src.main.java.com.smk.dao.LocationDao;
import FlightBooking.src.main.java.com.smk.model.dto.ScheduleDTO;
import com.smk.MainView;
import com.smk.dao.LocationDao;
import com.smk.model.Location;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.swing.*;
import java.awt.*;

@PageTitle("Create Booking")
@Route(value = "create booking", layout = MainView.class)
public class CreateBooking extends VerticalLayout {
    private LocationDao locationDao;
    public CreateBooking(){
        locationDao = new LocationDao();
        createForm();
    }

    private void createForm(){
        setAlignItems(Alignment.STRETCH);
        ComboBox<Location> fromComboBox = new ComboBox("Dari");
        fromComboBox.setItems(locationDao.getAll());
        fromComboBox.setItemLabelGenerator(Location::getName);

        ComboBox<Location> toComboBox = new ComboBox("Ke");
        toComboBox.setItems(locationDao.getAll());
        toComboBox.setItemLabelGenerator(Location::getName);
        DatePicker departureDatePicker = new DatePicker("Tanggal Keberangkatan");
        DatePicker arrivalDatePicker = new DatePicker("Tanggal Kepulangan");
        Button searchButton = new Button("Search");
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(fromComboBox, toComboBox, departureDatePicker, arrivalDatePicker, searchButton);

        Grid<ScheduleDTO> grid = new Grid<>(ScheduleDTO.class, autoCreateColumns: false);
        grid.addColumn(ScheduleDTO::getId).setHeader("Id");
        grid.addColumn(ScheduleDTO::getFlightNumber).setHeader("Nomor Pesawat");
        grid.addColumn(ScheduleDTO::getDepartureLocation).setHeader("Keberangkatan");
        grid.addColumn(ScheduleDTO::getArrivalLocation).setHeader("Kedatangan");
        grid.addColumn(ScheduleDTO::getDepartureDate).setHeader("Waktu Keberangkatan");
        
        add(fromComboBox,
                toComboBox,
                departureDatePicker,
                arrivalDatePicker,
                searchButton,
                grid);
    }
}
