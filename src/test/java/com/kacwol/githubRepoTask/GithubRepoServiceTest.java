package com.kacwol.githubRepoTask;

import com.kacwol.githubRepoTask.model.Branch;
import com.kacwol.githubRepoTask.model.Commit;
import com.kacwol.githubRepoTask.model.Repo;
import com.kacwol.githubRepoTask.model.RepoName;
import com.kacwol.githubRepoTask.model.Response;
import com.kacwol.githubRepoTask.model.TempBranch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GithubRepoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubRepoService service;


    @Test
    public void test() {
        String userName = "user";

        String sha1 = "sha1";
        String sha2 = "sha2";
        String sha3 = "sha3";

        String branchName1 = "branch1";
        String branchName2 = "branch2";
        String branchName3 = "branch3";

        String repoStringName1 = "repo1";
        String repoStringName2 = "repo2";
        String repoStringName3 = "repo3";

        RepoName repoName1 = new RepoName(repoStringName1, true);
        RepoName repoName2 = new RepoName(repoStringName2, false);
        RepoName repoName3 = new RepoName(repoStringName3, false);

        Commit commit1 = new Commit(sha1);
        Commit commit2 = new Commit(sha2);
        Commit commit3 = new Commit(sha3);

        TempBranch tempBranch1 = new TempBranch(branchName1, commit1);
        TempBranch tempBranch2 = new TempBranch(branchName2, commit2);
        TempBranch tempBranch3 = new TempBranch(branchName3, commit3);

        RepoName[] repoNames = {repoName1, repoName2, repoName3};

        Branch branch2 = new Branch(sha2, branchName2);
        Branch branch3 = new Branch(sha3, branchName3);

        Repo repo2 = new Repo(repoStringName2, List.of(branch2));
        Repo repo3 = new Repo(repoStringName3, List.of(branch3));



        Mockito.when(restTemplate.getForEntity(
                "https://api.github.com/users/" + userName + "/repos",
                RepoName[].class
        )).thenReturn(new ResponseEntity<>(repoNames, HttpStatus.OK));

        Mockito.when(restTemplate.getForObject(
                "https://api.github.com/repos/" + userName + "/" + repoName1.getName() + "/branches", TempBranch[].class
        )).thenReturn(new TempBranch[]{tempBranch1});

        Mockito.when(restTemplate.getForObject(
                "https://api.github.com/repos/" + userName + "/" + repoName2.getName() + "/branches", TempBranch[].class
        )).thenReturn(new TempBranch[]{tempBranch2});

        Mockito.when(restTemplate.getForObject(
                "https://api.github.com/repos/" + userName + "/" + repoName3.getName() + "/branches", TempBranch[].class
        )).thenReturn(new TempBranch[]{tempBranch3});



        Response expected = new Response(userName, List.of(repo2, repo3));
        Response actual = service.get(userName);

        Assert.assertEquals(expected,actual);

    }
}
