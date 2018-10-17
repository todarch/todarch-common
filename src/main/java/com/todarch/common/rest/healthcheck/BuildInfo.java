package com.todarch.common.rest.healthcheck;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Provides information about which version of application is running.
 * Can be helpful in a dynamic environment to know the details of deployed artifact while debugging.
 * https://circleci.com/blog/dockerizing-java-apps-with-circleci-and-jib/
 *
 * @author selimssevgi
 */
@Component
@Getter
@Setter
public class BuildInfo {

  @Value("${build.number:-1}")
  private String buildNum;

  @Value("${commit.hash:-1}")
  private String commitHash;

  @Value("${build.user:xyz}")
  private String commitUser;

  @Value("${build.repo:xyz}")
  private String repoName;

  @Value("${circle.workflow.guid:a1b2}")
  private String workflowGuid;


  protected static final String CCI_URL_PREFIX = "https://circleci.com/workflow-run/";
  protected static final String GH_URL_FORMAT = "https://github.com/%s/%s/commit/%s";

  public String getWorkflowUrl() {
    return CCI_URL_PREFIX + workflowGuid;
  }

  public String getGithubUrl() {
    return formatGithubUrl(commitUser,repoName,commitHash);
  }

  protected String formatGithubUrl(String user, String repo, String commitHash) {
    return String.format(GH_URL_FORMAT, user, repo, commitHash);
  }
}
