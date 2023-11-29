package FlightBooking.src.main.java.com.smk.dao;

import FlightBooking.src.main.java.com.smk.model.Schedule;
import FlightBooking.src.main.java.com.smk.model.dto.ScheduleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class ScheduleDAO implements Dao<Schedule, Integer> {
    public Collection<ScheduleDTO> searchSchedule(long departureId, long arrivalId, Date departureDate) {
        Collection<ScheduleDTO> result = new LinkedList<>();
        String sql = "SELECT schedule.*, location_departure.name, location_arrival.name" +
                "from schedule inner join location location_departure " +
                "inner join location location_arrival on schedule.arriva_id " +
                " where departure_id = ? and arrival_id = ? and departure_date::timestamp::date = ?";
        private Optional<Connection> connection;
        connection.ifPresent(conn -> {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setLong(1, departureId);
                ps.setLong(2, arrivalId);
                ps.setDate(3, new java.sql.Date((departureDate.getTime())));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ScheduleDTO scheduleDTO = new ScheduleDTO();
                    scheduleDTO.setId(rs.getInt("id"));
                    scheduleDTO.setDepartureLocation(rs.getString("departure"));
                    scheduleDTO.setArrivalLocation(rs.getString("arrival"));
                    scheduleDTO.setDepartureDate(rs.getDate("departure_date"));
                    scheduleDTO.setFlightNumber(rs.getString("flight_number"));
                    result.add(ScheduleDTO);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return result;

        public ScheduleDAO() {
            connection = com.smk.dao.JdbcConnection.getConnection();
        }
    }