package com.kacwol.githubRepoTask.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TempBranch {
    private String name;
    private Commit commit;

    public Branch toBranch(){
        return new Branch(commit.getSha(), name);
    }
}
