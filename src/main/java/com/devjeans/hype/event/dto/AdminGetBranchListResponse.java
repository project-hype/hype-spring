package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.BranchVO;

import lombok.Data;

/**
 * 브랜치 리스트 조회 Response DTO (어드민)
 * @author 조영욱
 * @since 2024.06.19
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.19  	조영욱        최초 생성
 * </pre>
 */

@Data
public class AdminGetBranchListResponse {

	List<GetBranchResponse> branchList = new ArrayList<>();
	
	public AdminGetBranchListResponse(List<BranchVO> branchVOList) {
		branchVOList.stream().forEach((branch) -> {
			branchList.add(new GetBranchResponse(branch));
		});
		
	}
	
	@Data
	public static class GetBranchResponse {
		private Long branchId;
		private String branchName;
		
		public GetBranchResponse(BranchVO branch) {
			this.branchId = branch.getBranchId();
			this.branchName = branch.getBranchName();
		}
	}
}
