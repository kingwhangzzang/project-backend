package com.king.projectbackend.controller;

import com.king.projectbackend.entity.*;
import com.king.projectbackend.prop.JwtTokenProvider;
import com.king.projectbackend.service.CommentService;
import com.king.projectbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CommentController {

}
