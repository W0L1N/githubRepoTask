package com.kacwol.githubRepoTask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class RepoName {

    private String name;

    private boolean fork;
}
