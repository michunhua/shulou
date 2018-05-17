package com.slloan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loan")
public class InformationView {

	
	//贷款信息查看详情
	/**
	 * loaninfo详情
	 * 借款人信息
	 * @return
	 */
	@RequestMapping(value = "/loaninfoone")
	public String loaninfoone() {
		System.out.println("--------------------------");
		return "loaninfo/loanerInfo";
	}
	
	/**
	 * loaninfo详情
	 * 共同借款人信息
	 * @return
	 */
	@RequestMapping(value = "/loaninfotwo")
	public String loaninfotwo() {
		System.out.println("--------------------------");
		return "loaninfo/commomLoanerInfo";
	}
	
	/**
	 * loaninfo详情
	 * 借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/loaninfolom")
	public String loaninfolom() {
		System.out.println("--------------------------");
		return "loaninfo/loanerMate";
	}
	
	/**
	 * loaninfo详情
	 * 借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/loaninfocomm")
	public String loaninfocomm() {
		System.out.println("--------------------------");
		return "loaninfo/commomMate";
	}
	
	/**
	 * loaninfo详情
	 * 借款信息
	 * @return
	 */
	@RequestMapping(value = "/loaninfothree")
	public String loaninfothree() {
		System.out.println("--------------------------");
		return "loaninfo/loanInfo";
	}
	
	/**
	 * loaninfo详情
	 * 房产信息
	 * @return
	 */
	@RequestMapping(value = "/loanestateInfo")
	public String loanestateInfo() {
		System.out.println("--------------------------");
		return "loaninfo/estateInfo";
	}
	
	/**
	 * loaninfo详情
	 * 联系人信息
	 * @return
	 */
	@RequestMapping(value = "/loanlinkInfo")
	public String loanlinkInfo() {
		System.out.println("--------------------------");
		return "loaninfo/linkInfo";
	}
	
	/**
	 * loaninfo详情
	 * 备注信息
	 * @return
	 */
	@RequestMapping(value = "/loannoteInfo")
	public String loannoteInfo() {
		System.out.println("--------------------------");
		return "loaninfo/financeNote";
	}
	
	/**
	 * 信息查看
	 * 图像上传
	 * @return
	 */
	@RequestMapping(value = "/imageInfoimg")
	public String imageInfoimg() {
		System.out.println("--------------------------");
		return "loaninfo/imageInfo";
	}
	//财务详情

	/**
	 * 财务详情
	 * 借款人	 */
	@RequestMapping(value = "/financeapp")
	public String financeapp() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/loanerInfo";
	}
	

	/**
	 * 财务详情
	 * 共同借款人
	 * @return
	 */
	@RequestMapping(value = "/financeappcomm")
	public String financeappscomm() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/commomLoanerInfo";
	}
	/**
	 * 财务详情
	 * 借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/financeapploan")
	public String financeapploan() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/loanerMate";
	}
	/**
	 * 财务详情
	 * 共同借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/financeappcommm")
	public String financeappcommm() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/commomMate";
	}
	
	/**
	 * 财务详情
	 * 借款申请信息
	 * @return
	 */
	@RequestMapping(value = "/financeapploanInfo")
	public String financeapploanInfo() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/loanInfo";
	}
	
	/**
	 * 财务详情
	 * 房产信息
	 * @return
	 */
	@RequestMapping(value = "/financeappest")
	public String financeappest() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/estateInfo";
	}
	
	/**
	 * 财务详情
	 * 联系人信息
	 * @return
	 */
	@RequestMapping(value = "/financeapplink")
	public String financeapplink() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/linkInfo";
	}
	
	/**
	 * 财务详情
	 * 备注信息
	 * @return
	 */
	@RequestMapping(value = "/financeappnot")
	public String financeappnot() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/financeNote";
	}
	/**
	 * 财务详情
	 * 影像上传
	 * @return
	 */
	@RequestMapping(value = "/financeappimg")
	public String financeappsimg() {
		System.out.println("--------------------------");
		return "financial/financeApproval/financialinfo/imageInfo";
	}
	
	//回款确认

	/**
	 * 回款确认
	 * 借款人信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfoloan")
	public String repayinfoloan() {
		System.out.println("--------------------------");
		return "repayinfo/loanerInfo";
	}
	
	/**
	 *回款确认
	 * 共同借款人信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfotwo")
	public String repayinfotwo() {
		System.out.println("--------------------------");
		return "repayinfo/commomLoanerInfo";
	}
	
	/**
	 * 回款确认
	 * 借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/repayinfolom")
	public String repayinfolom() {
		System.out.println("--------------------------");
		return "repayinfo/loanerMate";
	}
	
	/**
	 回款确认
	 * 共同借款人配偶
	 * @return
	 */
	@RequestMapping(value = "/repayinfocomm")
	public String repayinfocomm() {
		System.out.println("--------------------------");
		return "repayinfo/commomMate";
	}
	
	/**
	 回款确认
	 * 借款信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfothree")
	public String repayinfothree() {
		System.out.println("--------------------------");
		return "repayinfo/loanInfo";
	}
	
	/**
	 * 回款确认
	 * 房产信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfoestateInfo")
	public String repayinfoestateInfo() {
		System.out.println("--------------------------");
		return "repayinfo/estateInfo";
	}
	
	/**
	 * 回款确认
	 * 联系人信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfolinkInfo")
	public String repayinfolinkInfo() {
		System.out.println("--------------------------");
		return "repayinfo/linkInfo";
	}
	
	/**
	 * 回款确认
	 * 备注信息
	 * @return
	 */
	@RequestMapping(value = "/repayinfonoteInfo")
	public String repayinfonoteInfo() {
		System.out.println("--------------------------");
		return "repayinfo/financeNote";
	}
	
	/**
	 * 回款确认
	 * 图像上传
	 * @return
	 */
	@RequestMapping(value = "/repayinfoimg")
	public String repayinfoimg() {
		System.out.println("--------------------------");
		return "repayinfo/imageInfo";
	}
	
	
	
	//结算凭证
	 
	 /*
		 * 结算凭证
		 * 借款人信息
		 * @return
		 */
		@RequestMapping(value = "/finncilclearloan")
		public String finncilclearloan() {
			System.out.println("--------------------------");
			return "finncilclear/loanerInfo";
		}
		
		/**
		 结算凭证
		 * 共同借款人信息
		 * @return
		 */
		@RequestMapping(value = "/finncilcleartwo")
		public String finncilcleartwo() {
			System.out.println("--------------------------");
			return "finncilclear/commomLoanerInfo";
		}
		
		/**
		 * 结算凭证
		 * 借款人配偶
		 * @return
		 */
		@RequestMapping(value = "/finncilclearlom")
		public String finncilclearlom() {
			System.out.println("--------------------------");
			return "finncilclear/loanerMate";
		}
		
		/**
		 结算凭证
		 * 共同借款人配偶
		 * @return
		 */
		@RequestMapping(value = "/finncilclearcomm")
		public String finncilclearcomm() {
			System.out.println("--------------------------");
			return "finncilclear/commomMate";
		}
		
		/**
		 结算凭证
		 * 借款信息
		 * @return
		 */
		@RequestMapping(value = "/finncilclearthree")
		public String finncilclearthree() {
			System.out.println("--------------------------");
			return "finncilclear/loanInfo";
		}
		
		/**
		 * 结算凭证
		 * 房产信息
		 * @return
		 */
		@RequestMapping(value = "/finncilclearestateInfo")
		public String finncilclearestateInfo() {
			System.out.println("--------------------------");
			return "finncilclear/estateInfo";
		}
		
		/**
		 * 结算凭证
		 * 联系人信息
		 * @return
		 */
		@RequestMapping(value = "/finncilclearlinkInfo")
		public String finncilclearlinkInfo() {
			System.out.println("--------------------------");
			return "finncilclear/linkInfo";
		}
		
		/**
		 * 结算凭证
		 * 备注信息
		 * @return
		 */
		@RequestMapping(value = "/finncilclearnoteInfo")
		public String finncilclearnoteInfo() {
			System.out.println("--------------------------");
			return "finncilclear/financeNote";
		}
		
		/**
		 * 结算凭证
		 * 图像上传
		 * @return
		 */
		@RequestMapping(value = "/finncilclearimg")
		public String finncilclearimg() {
			System.out.println("--------------------------");
			return "finncilclear/imageInfo";
		}
		
		// 解押凭证
		
		/**
		 * 解押凭证
		 * 借款人信息
		 * @return
		 */
		@RequestMapping(value = "/lrelieinfoloan")
		public String lrelieinfoloan() {
			System.out.println("--------------------------");
			return "relieinfo/loanerInfo";
		}
		
		/**
		 *解押凭证
		 * 共同借款人信息
		 * @return
		 */
		@RequestMapping(value = "/relieinfotwo")
		public String relieinfotwo() {
			System.out.println("--------------------------");
			return "relieinfo/commomLoanerInfo";
		}
		
		/**
		 * 解押凭证
		 * 借款人配偶
		 * @return
		 */
		@RequestMapping(value = "/relieinfolom")
		public String relieinfolom() {
			System.out.println("--------------------------");
			return "relieinfo/loanerMate";
		}
		
		/**
		 * 解押凭证
		 * 借款人配偶
		 * @return
		 */
		@RequestMapping(value = "/relieinfocomm")
		public String relieinfocomm() {
			System.out.println("--------------------------");
			return "relieinfo/commomMate";
		}
		
		/**
		 *解押凭证
		 * 借款信息
		 * @return
		 */
		@RequestMapping(value = "/relieinfothree")
		public String relieinfothree() {
			System.out.println("--------------------------");
			return "relieinfo/loanInfo";
		}
		
		/**
		 * 解押凭证
		 * 房产信息
		 * @return
		 */
		@RequestMapping(value = "/relieinfoestateInfo")
		public String relieinfoestateInfo() {
			System.out.println("--------------------------");
			return "relieinfo/estateInfo";
		}
		
		/**
		 * 解押凭证
		 * 联系人信息
		 * @return
		 */
		@RequestMapping(value = "/relieinfolinkInfo")
		public String relieinfolinkInfo() {
			System.out.println("--------------------------");
			return "relieinfo/linkInfo";
		}
		
		/**
		 * 解押凭证
		 * 备注信息
		 * @return
		 */
		@RequestMapping(value = "/relieinfonoteInfo")
		public String relieinfonoteInfo() {
			System.out.println("--------------------------");
			return "relieinfo/financeNote";
		}
		
		/**
		 * 解押凭证
		 * 图像上传
		 * @return
		 */
		@RequestMapping(value = "/relieinfoimg")
		public String relieinfoimg() {
			System.out.println("--------------------------");
			return "relieinfo/imageInfo";
		}
		
		//进押凭证
		
		 
		 /*
			 *  进押凭证
			 * 借款人信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfoloan")
			public String incareinfoloan() {
				System.out.println("--------------------------");
				return "incareinfo/loanerInfo";
			}
			
			/**
			  进押凭证
			 * 共同借款人信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfotwo")
			public String incareinfotwo() {
				System.out.println("--------------------------");
				return "incareinfo/commomLoanerInfo";
			}
			
			/**
			 *  进押凭证
			 * 借款人配偶
			 * @return
			 */
			@RequestMapping(value = "/incareinfolom")
			public String rincareinfolom() {
				System.out.println("--------------------------");
				return "incareinfo/loanerMate";
			}
			
			/**
			  进押凭证
			 * 共同借款人配偶
			 * @return
			 */
			@RequestMapping(value = "/incareinfocomm")
			public String incareinfocomm() {
				System.out.println("--------------------------");
				return "incareinfo/commomMate";
			}
			
			/**
			  进押凭证
			 * 借款信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfothree")
			public String incareinfothree() {
				System.out.println("--------------------------");
				return "incareinfo/loanInfo";
			}
			
			/**
			 *  进押凭证
			 * 房产信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfoestateInfo")
			public String incareinfoestateInfo() {
				System.out.println("--------------------------");
				return "incareinfo/estateInfo";
			}
			
			/**
			 *  进押凭证
			 * 联系人信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfolinkInfo")
			public String incareinfolinkInfo() {
				System.out.println("--------------------------");
				return "incareinfo/linkInfo";
			}
			
			/**
			 *  进押凭证
			 * 备注信息
			 * @return
			 */
			@RequestMapping(value = "/incareinfonoteInfo")
			public String incareinfonoteInfo() {
				System.out.println("--------------------------");
				return "incareinfo/financeNote";
			}
			
			/**
			 *  进押凭证
			 * 图像上传
			 * @return
			 */
			@RequestMapping(value = "/incareinfoimg")
			public String incareinfoimg() {
				System.out.println("--------------------------");
				return "incareinfo/imageInfo";
			}
			
			//取证凭证
			
			 
			 	/*
				 *  取证凭证
				 * 借款人信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfoloan")
				public String gaininfoloan() {
					System.out.println("--------------------------");
					return "gaininfo/loanerInfo";
				}
				
				/**
				  取证凭证
				 * 共同借款人信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfotwo")
				public String gaininfotwo() {
					System.out.println("--------------------------");
					return "gaininfo/commomLoanerInfo";
				}
				
				/**
				 *  取证凭证
				 * 借款人配偶
				 * @return
				 */
				@RequestMapping(value = "/gaininfolom")
				public String gaininfolom() {
					System.out.println("--------------------------");
					return "gaininfo/loanerMate";
				}
				
				/**
				  取证凭证
				 * 共同借款人配偶
				 * @return
				 */
				@RequestMapping(value = "/gaininfocomm")
				public String gaininfocomm() {
					System.out.println("--------------------------");
					return "gaininfo/commomMate";
				}
				
				/**
				  取证凭证
				 * 借款信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfothree")
				public String gaininfothree() {
					System.out.println("--------------------------");
					return "gaininfo/loanInfo";
				}
				
				/**
				 *  取证凭证
				 * 房产信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfoestateInfo")
				public String gaininfoestateInfo() {
					System.out.println("--------------------------");
					return "gaininfo/estateInfo";
				}
				
				/**
				 *  取证凭证
				 * 联系人信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfolinkInfo")
				public String gaininfolinkInfo() {
					System.out.println("--------------------------");
					return "gaininfo/linkInfo";
				}
				
				/**
				 *  取证凭证
				 * 备注信息
				 * @return
				 */
				@RequestMapping(value = "/gaininfonoteInfo")
				public String gaininfonoteInfo() {
					System.out.println("--------------------------");
					return "gaininfo/financeNote";
				}
				
				/**
				 *  取证凭证
				 * 图像上传
				 * @return
				 */
				@RequestMapping(value = "/gaininfoimg")
				public String gaininfoimg() {
					System.out.println("--------------------------");
					return "gaininfo/imageInfo";
				}
				
				//转账凭证
				 
				/*
					 * 转账凭证
					 * 借款人信息
					 * @return
					 */
					@RequestMapping(value = "/financiltransloan")
					public String financiltransloan() {
						System.out.println("--------------------------");
						return "financiltrans/loanerInfo";
					}
					
					/**
					 转账凭证
					 * 共同借款人信息
					 * @return
					 */
					@RequestMapping(value = "/financiltranstwo")
					public String financiltranstwo() {
						System.out.println("--------------------------");
						return "financiltrans/commomLoanerInfo";
					}
					
					/**
					 * 转账凭证
					 * 借款人配偶
					 * @return
					 */
					@RequestMapping(value = "/financiltranslom")
					public String financiltranslom() {
						System.out.println("--------------------------");
						return "financiltrans/loanerMate";
					}
					
					/**
					 转账凭证
					 * 共同借款人配偶
					 * @return
					 */
					@RequestMapping(value = "/financiltranscomm")
					public String financiltranscomm() {
						System.out.println("--------------------------");
						return "financiltrans/commomMate";
					}
					
					/**
					 转账凭证
					 * 借款信息
					 * @return
					 */
					@RequestMapping(value = "/financiltransthree")
					public String financiltransthree() {
						System.out.println("--------------------------");
						return "financiltrans/loanInfo";
					}
					
					/**
					 * 转账凭证
					 * 房产信息
					 * @return
					 */
					@RequestMapping(value = "/financiltransestateInfo")
					public String financiltransestateInfo() {
						System.out.println("--------------------------");
						return "financiltrans/estateInfo";
					}
					
					/**
					 * 转账凭证
					 * 联系人信息
					 * @return
					 */
					@RequestMapping(value = "/financiltranslinkInfo")
					public String financiltranslinkInfo() {
						System.out.println("--------------------------");
						return "financiltrans/linkInfo";
					}
					
					/**
					 * 转账凭证
					 * 备注信息
					 * @return
					 */
					@RequestMapping(value = "/financiltransnoteInfo")
					public String financiltransnoteInfo() {
						System.out.println("--------------------------");
						return "financiltrans/financeNote";
					}
					
					/**
					 * 转账凭证
					 * 图像上传
					 * @return
					 */
					@RequestMapping(value = "/financiltransimg")
					public String financiltransimg() {
						System.out.println("--------------------------");
						return "financiltrans/imageInfo";
					}


}
