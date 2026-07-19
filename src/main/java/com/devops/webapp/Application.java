package com.devops.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@Controller
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String index(Model model) {
        String podName = System.getenv("HOSTNAME");
        if (podName == null || podName.isEmpty()) {
            try {
                podName = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException e) {
                podName = "Unknown-Host-Node";
            }
        }

        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalMemorySize();
        
        model.addAttribute("podName", podName);
        model.addAttribute("os", System.getProperty("os.name"));
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("totalMem", (memorySize / (1024 * 1024)) + " MB");
        model.addAttribute("status", "SECURE & OPERATIONAL");
        
        return "index";
    }
}
