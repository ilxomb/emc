package com.barust.emc.controller;

import com.barust.emc.ResponseType;
import com.barust.emc.service.JsfoInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.barust.emc.entity.JsfoInfo;
import com.barust.emc.entity.data.JsfoData;


import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Адлия вазирлиги (8.3.)", description = "Фарзандликка олиш бўйича маълумотлар")

public class JsfoController {

    //8.3. Фарзандликка олиш бўйича маълумотларни етказиб бериш
    @Autowired
    JsfoInfoService jsfoInfoService;
    @PostMapping("/jsfo/add")
    @Operation(summary = "Фарзандликка олиш бўйича маълумотларни киритиш", description = "Фарзандликка олиш бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsfo(@RequestBody JsfoInfo jsfoInformation) throws Exception {
        return ResponseEntity.ok(jsfoInfoService.save(jsfoInformation));
    }

    @GetMapping("/jsfo/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoInfo.class))))})
    public ResponseEntity<?> list_jsfo( @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsfoInfo> list = jsfoInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsfoInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsfo/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoInfo.class))))})
    public ResponseEntity<?> list_jsfo_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsfoInfoService.findByDate(sana));
    }

    @GetMapping("/jsfo/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoData.class))))})
    public ResponseEntity<?> list_jsfo_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsfoInfoService.findByJSHSHIR(jshshir));
    }
}
