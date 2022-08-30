package com.kacwol.githubRepoTask;

import com.kacwol.githubRepoTask.exception.UserNotFoundException;
import com.kacwol.githubRepoTask.model.Repo;
import com.kacwol.githubRepoTask.model.RepoName;
import com.kacwol.githubRepoTask.model.Response;
import com.kacwol.githubRepoTask.model.TempBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GithubRepoService {

    private final RestTemplate restTemplate;

    @Autowired
    public GithubRepoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Response get(String name){
        List<Repo> repos = new ArrayList<>();

        List<RepoName> response = getRepoNames(name)
                .stream()
                .filter(repoName -> !repoName.isFork())
                .toList();

        for (RepoName model : response) {
            List<TempBranch> branches = getBranches(name, model.getName());

            Repo repo = new Repo(
                    model.getName(),
                    branches.stream()
                            .map(TempBranch::toBranch)
                            .toList()
            );
            repos.add(repo);
        }
        return new Response(name, repos);
    }


    private List<TempBranch> getBranches(String userName, String repoName){
        return List.of(
                Objects.requireNonNull(
                        restTemplate.getForObject(
                                "https://api.github.com/repos/" + userName + "/" + repoName + "/branches", TempBranch[].class
                        )
                )
        );
    }

    private List<RepoName> getRepoNames(String name){
        ResponseEntity<RepoName[]> repoNames = null;
        try{
            repoNames = restTemplate.getForEntity("https://api.github.com/users/" + name + "/repos", RepoName[].class);

        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                System.out.println("XD");
                throw new UserNotFoundException("User not found.");
            } else {
                e.printStackTrace();
            }
        }

        return Arrays.asList(Objects.requireNonNull(repoNames.getBody()));
    }

}
