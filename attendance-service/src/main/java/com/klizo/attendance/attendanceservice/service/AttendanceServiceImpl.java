package com.klizo.attendance.attendanceservice.service;

import com.klizo.attendance.attendanceservice.dto.*;
import com.klizo.attendance.attendanceservice.entity.*;
import com.klizo.attendance.attendanceservice.enumeration.NotificationType;
import com.klizo.attendance.attendanceservice.enumeration.RequestStatus;
import com.klizo.attendance.attendanceservice.exception.UnauthorizedAccessException;
import com.klizo.attendance.attendanceservice.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final ClockInOutRepository clockInOutRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final ShiftChangeRequestRepository shiftChangeRequestRepository;
    private final HolidayRepository holidayRepository;
    private final EarlyLeaveRequestRepository earlyLeaveRequestRepository;
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final DailySummaryRepository dailySummaryRepository;
    private final NotificationRepository notificationRepository;
    private final JwtService jwtService;

    public AttendanceServiceImpl(ClockInOutRepository clockInOutRepository,
                                LeaveRequestRepository leaveRequestRepository,
                                ShiftChangeRequestRepository shiftChangeRequestRepository,
                                HolidayRepository holidayRepository,
                                EarlyLeaveRequestRepository earlyLeaveRequestRepository,
                                ProjectAssignmentRepository projectAssignmentRepository,
                                DailySummaryRepository dailySummaryRepository,
                                NotificationRepository notificationRepository,
                                JwtService jwtService) {
        this.clockInOutRepository = clockInOutRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.shiftChangeRequestRepository = shiftChangeRequestRepository;
        this.holidayRepository = holidayRepository;
        this.earlyLeaveRequestRepository = earlyLeaveRequestRepository;
        this.projectAssignmentRepository = projectAssignmentRepository;
        this.dailySummaryRepository = dailySummaryRepository;
        this.notificationRepository = notificationRepository;
        this.jwtService = jwtService;
    }


//    @Override
//    public String recordClockIn(String token , ClockType type) {
//        String username = jwtService.extractUsername(token);
//        return "Clock-in recorded for user " + username + " at " + LocalDateTime.now();
//    }
//
//    @Override
//    public String recordClockOut(String token , ClockType type) {
//        String username = jwtService.extractUsername(token);
//        return "Clock-out recorded for user " + username + " at " + LocalDateTime.now();
//    }

    @Override
    public ApiResponseDto<ClockInOutResponseDto> clockIn(String token, ClockInRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);

        ClockInOut clockInOut = ClockInOut.builder()
                .employeeId(employeeId)
                .firstName(jwtService.getFirstName(token))
                .lastName(jwtService.getLastName(token))
                .date(LocalDate.now())
                .inTime(LocalDateTime.now())
                .type(request.getType())
                .build();
        
        clockInOutRepository.save(clockInOut);

        ClockInOutResponseDto response = new ClockInOutResponseDto(
                employeeId,
                clockInOut.getFirstName(),
                clockInOut.getLastName(),
                LocalDateTime.now(),
                "CLOCK_IN"
        );
        
        return ApiResponseDto.success(clockInOut.getFirstName() + " " + clockInOut.getLastName() + ", Clocking in!", response);
    }

    @Override
    public ApiResponseDto<ClockInOutResponseDto> clockOut(String token, ClockOutRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);

        ClockInOut clockInOut = ClockInOut.builder()
                .employeeId(employeeId)
                .firstName(jwtService.getFirstName(token))
                .lastName(jwtService.getLastName(token))
                .date(LocalDate.now())
                .outTime(LocalDateTime.now())
                .type(request.getType())
                .build();
        
        clockInOutRepository.save(clockInOut);

        updateDailySummary(employeeId, LocalDate.now());

        ClockInOutResponseDto response = new ClockInOutResponseDto(
                employeeId,
                clockInOut.getFirstName(),
                clockInOut.getLastName(),
                LocalDateTime.now(),
                "CLOCK_OUT"
        );
        
        return ApiResponseDto.success( clockInOut.getFirstName() + " " + clockInOut.getLastName() +", Clocking out!", response);
    }

    @Override
    public ApiResponseDto<ClockInOutResponseDto> Break(String token, BreakRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);

        ClockInOut clockInOut = ClockInOut.builder()
                .employeeId(employeeId)
                .firstName(jwtService.getFirstName(token))
                .lastName(jwtService.getLastName(token))
                .date(LocalDate.now())
                .outTime(LocalDateTime.now())
                .type(request.getType())
                .build();

        clockInOutRepository.save(clockInOut);

        updateDailySummary(employeeId, LocalDate.now());

        ClockInOutResponseDto response = new ClockInOutResponseDto(
                employeeId,
                clockInOut.getFirstName(),
                clockInOut.getLastName(),
                LocalDateTime.now(),
                "CLOCK_OUT"
        );

        return ApiResponseDto.success( clockInOut.getFirstName() + " " + clockInOut.getLastName() +", Clocking out!", response);
    }



    @Override
    public ApiResponseDto<LeaveResponseDto> submitLeaveRequest(String token, LeaveRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);

        LeaveRequest leaveRequest = LeaveRequest.builder()
                .employeeId(employeeId)
                .firstName(jwtService.getFirstName(token))
                .lastName(jwtService.getLastName(token))
                .type(request.getType())
                .fromDate(request.getFromDate())
                .toDate(request.getToDate())
                .reason(request.getReason())
                .status(RequestStatus.PENDING)
                .build();
        
        leaveRequestRepository.save(leaveRequest);

        createNotification(employeeId, "Leave request submitted", NotificationType.IN_APP);

        LeaveResponseDto response = new LeaveResponseDto(
                leaveRequest.getId(),
                employeeId,
                leaveRequest.getFirstName(),
                leaveRequest.getLastName(),
                request.getType(),
                request.getFromDate(),
                request.getToDate(),
                request.getReason(),
                RequestStatus.PENDING
        );
        
        return ApiResponseDto.success("Leave request submitted successfully: ", response);
    }

    @Override
    public ApiResponseDto<List<LeaveResponseDto>> getLeaveRequests(String token) {
        return ApiResponseDto.success("Leave requests retrieved successfully", List.of());
    }

    @Override
    public ApiResponseDto<LeaveResponseDto> updateLeaveRequest(String token, Long requestId, LeaveRequestDto request) {
        // Implementation for updating a leave request
        return ApiResponseDto.success("Leave request updated successfully", new LeaveResponseDto());
    }

    @Override
    public ApiResponseDto<ShiftChangeResponseDto> requestShiftChange(String token, ShiftChangeRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);
        
        ShiftChangeRequest shiftChange = ShiftChangeRequest.builder()
                .employeeId(employeeId)
                .firstName("First Name")
                .lastName("Last Name")
                .requestedDate(request.getRequestedDate())
                .fromShift(request.getFromShift())
                .toShift(request.getToShift())
                .reason(request.getReason())
                .status(RequestStatus.PENDING)
                .build();
        
        shiftChangeRequestRepository.save(shiftChange);
        
        createNotification(employeeId, "Shift change request submitted", NotificationType.IN_APP);
        
        ShiftChangeResponseDto response = new ShiftChangeResponseDto(
                shiftChange.getId(),
                employeeId,
                "First Name",
                "Last Name",
                request.getRequestedDate(),
                request.getFromShift(),
                request.getToShift(),
                request.getReason(),
                RequestStatus.PENDING
        );
        
        return ApiResponseDto.success("Shift change request submitted successfully", response);
    }

    @Override
    public ApiResponseDto<List<ShiftChangeResponseDto>> getShiftChangeRequests(String token) {
        Long employeeId = jwtService.getEmployeeId(token);
        List<ShiftChangeRequest> requests = shiftChangeRequestRepository.findByEmployeeId(employeeId);
        
        List<ShiftChangeResponseDto> responses = requests.stream()
                .map(request -> new ShiftChangeResponseDto(
                        request.getId(),
                        request.getEmployeeId(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getRequestedDate(),
                        request.getFromShift(),
                        request.getToShift(),
                        request.getReason(),
                        request.getStatus()
                ))
                .collect(Collectors.toList());
        
        return ApiResponseDto.success("Shift change requests retrieved successfully", responses);
    }

    @Override
    public ApiResponseDto<HolidayResponseDto> addHoliday(String token, HolidayRequestDto request) {
        if (!jwtService.extractRole(token).equals("DIRECTOR")) {
            throw new UnauthorizedAccessException("Only administrators can add holidays");
        }
        
        Holiday holiday = Holiday.builder()
                .name(request.getName())
                .date(request.getDate())
                .description(request.getDescription())
                .build();
        
        holidayRepository.save(holiday);
        
        HolidayResponseDto response = new HolidayResponseDto(
                holiday.getId(),
                request.getName(),
                request.getDate(),
                request.getDescription()
        );
        
        return ApiResponseDto.success("Holiday added successfully", response);
    }

    @Override
    public ApiResponseDto<List<HolidayResponseDto>> getAllHolidays() {
        List<Holiday> holidays = holidayRepository.findAll();
        
        List<HolidayResponseDto> responses = holidays.stream()
                .map(holiday -> new HolidayResponseDto(
                        holiday.getId(),
                        holiday.getName(),
                        holiday.getDate(),
                        holiday.getDescription()
                ))
                .collect(Collectors.toList());
        
        return ApiResponseDto.success("Holidays retrieved successfully", responses);
    }

    @Override
    public ApiResponseDto<EarlyLeaveResponseDto> requestEarlyLeave(String token, EarlyLeaveRequestDto request) {
        Long employeeId = jwtService.getEmployeeId(token);
        
        EarlyLeaveRequest earlyLeave = EarlyLeaveRequest.builder()
                .employeeId(employeeId)
                .firstName("First Name")
                .lastName("Last Name")
                .date(request.getDate())
                .leaveTime(request.getLeaveTime())
                .reason(request.getReason())
                .status(RequestStatus.PENDING)
                .build();
        
        earlyLeaveRequestRepository.save(earlyLeave);
        
        createNotification(employeeId, "Early leave request submitted", NotificationType.IN_APP);
        
        EarlyLeaveResponseDto response = new EarlyLeaveResponseDto(
                earlyLeave.getId(),
                employeeId,
                "First Name",
                "Last Name",
                request.getDate(),
                request.getLeaveTime(),
                request.getReason(),
                RequestStatus.PENDING
        );
        
        return ApiResponseDto.success("Early leave request submitted successfully", response);
    }

    @Override
    public ApiResponseDto<List<EarlyLeaveResponseDto>> getEarlyLeaveRequests(String token) {
        Long employeeId = jwtService.getEmployeeId(token);
        List<EarlyLeaveRequest> requests = earlyLeaveRequestRepository.findByEmployeeId(employeeId);
        
        List<EarlyLeaveResponseDto> responses = requests.stream()
                .map(request -> new EarlyLeaveResponseDto(
                        request.getId(),
                        request.getEmployeeId(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getDate(),
                        request.getLeaveTime(),
                        request.getReason(),
                        request.getStatus()
                ))
                .collect(Collectors.toList());
        
        return ApiResponseDto.success("Early leave requests retrieved successfully", responses);
    }

    @Override
    public ApiResponseDto<ProjectAssignmentResponseDto> assignProject(String token, ProjectAssignmentRequestDto request) {
        if (!jwtService.extractRole(token).equals("MANAGER")) {
            throw new UnauthorizedAccessException("Only managers can assign projects");
        }
        
        ProjectAssignment assignment = ProjectAssignment.builder()
                .employeeId(jwtService.getEmployeeId(token))
                .firstName("First Name")
                .lastName("Last Name")
                .projectName(request.getProjectName())
                .projectManager(request.getProjectManager())
                .assignedFrom(request.getAssignedFrom())
                .assignedTo(request.getAssignedTo())
                .isActive(true)
                .build();
        
        projectAssignmentRepository.save(assignment);
        
        createNotification(jwtService.getEmployeeId(token), "New project assignment: " + request.getProjectName(), NotificationType.IN_APP);
        
        ProjectAssignmentResponseDto response = new ProjectAssignmentResponseDto(
                assignment.getId(),
                jwtService.getEmployeeId(token),
                "First Name",
                "Last Name",
                request.getProjectName(),
                request.getProjectManager(),
                request.getAssignedFrom(),
                request.getAssignedTo(),
                true
        );
        
        return ApiResponseDto.success("Project assigned successfully", response);
    }

    @Override
    public ApiResponseDto<List<ProjectAssignmentResponseDto>> getProjectAssignments(String token, Long employeeId) {
        List<ProjectAssignment> assignments = projectAssignmentRepository.findByEmployeeId(employeeId);
        
        List<ProjectAssignmentResponseDto> responses = assignments.stream()
                .map(assignment -> new ProjectAssignmentResponseDto(
                        assignment.getId(),
                        assignment.getEmployeeId(),
                        assignment.getFirstName(),
                        assignment.getLastName(),
                        assignment.getProjectName(),
                        assignment.getProjectManager(),
                        assignment.getAssignedFrom(),
                        assignment.getAssignedTo(),
                        assignment.getIsActive()
                ))
                .collect(Collectors.toList());
        
        return ApiResponseDto.success("Project assignments retrieved successfully", responses);
    }

    @Override
    public ApiResponseDto<DailySummaryDto> getDailySummary(String token, LocalDate date) {
        Long employeeId = jwtService.getEmployeeId(token);
        DailySummary summary = dailySummaryRepository.findByEmployeeIdAndDate(employeeId, date);
        
        DailySummaryDto response = new DailySummaryDto(
                summary.getId(),
                summary.getEmployeeId(),
                summary.getFirstName(),
                summary.getLastName(),
                summary.getDate(),
                summary.getTotalWorkedMinutes(),
                summary.getTotalBreakMinutes(),
                summary.getExceededBreakLimit()
        );
        
        return ApiResponseDto.success("Daily summary retrieved successfully", response);
    }

    private void createNotification(Long employeeId, String message, NotificationType type) {
        Notification notification = Notification.builder()
                .employeeId(employeeId)
                .message(message)
                .type(type)
                .isRead(false)
                .build();
        
        notificationRepository.save(notification);
    }

    private void updateDailySummary(Long employeeId, LocalDate date) {
        List<ClockInOut> records = clockInOutRepository.findByEmployeeIdAndDate(employeeId, date);
        
        long totalWorkedMinutes = calculateTotalWorkedMinutes(records);
        long totalBreakMinutes = calculateTotalBreakMinutes(records);
        
        DailySummary summary = dailySummaryRepository.findByEmployeeIdAndDate(employeeId, date);
        
        summary.setEmployeeId(employeeId);
        summary.setDate(date);
        summary.setTotalWorkedMinutes(totalWorkedMinutes);
        summary.setTotalBreakMinutes(totalBreakMinutes);
        summary.setExceededBreakLimit(totalBreakMinutes > 60);
        dailySummaryRepository.save(summary);
    }

    private long calculateTotalWorkedMinutes(List<ClockInOut> records) {
        return 0L;
    }

    private long calculateTotalBreakMinutes(List<ClockInOut> records) {
        return 0L;
    }
}