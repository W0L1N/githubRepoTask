package com.kacwol.githubRepoTask;


import com.kacwol.githubRepoTask.exception.NotAcceptableException;
import com.kacwol.githubRepoTask.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repos")
public class AppController {

    private final GithubRepoService repoService;

    @Autowired
    public AppController(GithubRepoService repoService) {
        this.repoService = repoService;
    }


    @GetMapping
    public Response get(@RequestHeader(HttpHeaders.ACCEPT) String accept, @RequestParam String name){

        if(accept.equals("application/xml")){
            throw new NotAcceptableException("Application/xml is not acceptable");
        }else {
            return repoService.get(name);
        }
    }


}
