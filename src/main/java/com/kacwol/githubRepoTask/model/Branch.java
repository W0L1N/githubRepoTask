package com.kacwol.githubRepoTask.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Branch {

    private String sha;

    private String name;
}
