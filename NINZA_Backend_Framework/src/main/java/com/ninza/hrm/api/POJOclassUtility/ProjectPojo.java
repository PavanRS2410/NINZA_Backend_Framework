package com.ninza.hrm.api.POJOclassUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPojo 
{
	  private String projectName;
	  private String createdBy;
	  private String status;
	  private int teamSize;
}
