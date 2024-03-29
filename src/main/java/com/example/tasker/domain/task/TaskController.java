package com.example.tasker.domain.task;

import com.example.tasker.domain.task.dto.GetTasksRes;
import com.example.tasker.domain.task.dto.PostTaskReq;
import com.example.tasker.domain.task.dto.PostTaskRes;
import com.example.tasker.domain.task.dto.*;
import com.example.tasker.domain.task.service.TaskService;
import com.example.tasker.domain.user.entity.User;
import com.example.tasker.global.dto.ApplicationResponse;
import com.example.tasker.global.dto.BaseException;
import com.example.tasker.global.dto.ErrorResponse;
import com.example.tasker.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/task")
@RequiredArgsConstructor
@Api(tags = "Task API", value = "Task 관련 API")
public class TaskController {

    private final TaskService taskService;
    private final JwtService jwtService;

    @Operation(summary = "Task 생성", description = "리스트형, 카테고리형 Task 생성, Header input : 엑세스 토큰, date 형식 : 20230707")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
    })
    @PostMapping("/home/{date}")
    public ApplicationResponse<PostTaskRes> createTask(@RequestBody @Valid PostTaskReq postTaskReq,
                                                       @PathVariable("date") String date) {
        User user = jwtService.getUser();
        return ApplicationResponse.create(taskService.createTask(user, postTaskReq, date));
    }

    @Operation(summary = "날짜별 Tasks 조회", description = "리스트형, 카테고리형 날짜별 Tasks 조회, Header input : 엑세스 토큰, date 형식 : 20230707")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "2003", description = "권한이 없는 유저의 접근입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/home/{date}")
    public ApplicationResponse<List<GetTasksRes>> getTasksByDate(@PathVariable("date") String date) {
        User user = jwtService.getUser();
        return ApplicationResponse.ok(taskService.getTasksByDate(user, date));
    }

    @Operation(summary = "Task 상태 변경", description = " 0 : 미완료, 1 : 완료")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "400", description = "해당 테스크를 찾을 수 없습니다.(T0001)", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/home/{task_id}/status")
    public ApplicationResponse<GetTasksRes> checkTask(@PathVariable("task_id") Long taskId) {
        User user = jwtService.getUser();
        return ApplicationResponse.ok(taskService.checkTask(user, taskId));
    }

    @Operation(summary = "Task 삭제", description = "리스트형, 카테고리형 날짜별 Task 삭제, Header input : 엑세스 토큰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "2003", description = "권한이 없는 유저의 접근입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{taskId}")
    public ApplicationResponse<Long> deleteTask(@PathVariable Long taskId) throws BaseException {
        User user = jwtService.getUser();
        taskService.deleteTask(user, taskId);
        return ApplicationResponse.ok();
    }

    // 수정 필요
    @Operation(summary = "Task 상세 수정", description = "테스크 상세보기 수정, Header input : 엑세스 토큰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "2003", description = "권한이 없는 유저의 접근입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{task_id}")
    public ApplicationResponse<PatchTaskDetailRes> editTaskDetail(@RequestBody @Valid PutTaskDetailReq putTaskDetailReq,
                                                                  @PathVariable("task_id") Long taskId) throws BaseException {
        User user = jwtService.getUser();
        return ApplicationResponse.create(taskService.editTaskDetail(user, putTaskDetailReq, taskId));
    }

    @Operation(summary = "task 하나 보기", description = "Header input : 엑세스 토큰")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "2003", description = "권한이 없는 유저의 접근입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{task_id}")
    public ApplicationResponse<GetTaskRes> readTask(@PathVariable("task_id") Long taskId) {
        User user = jwtService.getUser();
        return ApplicationResponse.ok(taskService.readTask(user, taskId));
    }

    // 테스크 북 발행
//    @PostMapping("/book")
//    public ApplicationResponse<?> createBook(){
//
//    }
}
