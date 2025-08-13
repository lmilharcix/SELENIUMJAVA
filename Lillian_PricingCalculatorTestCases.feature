@Update_Parameters
Feature: verify PRICING CALCULATOR functionality of dashboard

  @abc1
  Scenario: verify calculator tittle
    Given I login into web app without logging
    When user enter email 'ssotest.user5@vistajet.com' and password 'gshhH8**'
    And user click on sign in button
    Then verify user login successful and get authorization header

 
  #######ETS FEE
  @Calculator
  Scenario Outline: verify ETS fee is $358.00 per leg  for NREGS categories, OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify ETS fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | fee   |
      | "KTEB"   | "KIAD"   | "XO MEMBERSHIP" | "358" |
		    #Caribbean
      | "KMIA"   | "TJSJ"   | "XO MEMBERSHIP" | "358" |
		    #Alaska
      | "KTEB"   | "PANC"   | "XO MEMBERSHIP" | "358" |
		    #Hawaii
      | "KLAX"   | "PHNL"   | "XO MEMBERSHIP" | "358" |

  @Calculator
  Scenario Outline: verify ETS fee is $358.00 per leg for NREGS categories , RT - domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify ETS fee <fee> for the domestic categories trip <trip> round trip
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee   | trip |
      | "KTEB"   | "KIAD"   | "KIAD"    | "KTEB"   |"XO MEMBERSHIP" | "358" |	1    |


  @Calculator
  Scenario Outline: verify ETS fee is Not displayed for XO-LITE  , OW -domestic .LILIAN 04_30_25
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only XoLite Category
    #And select Only xojet lite and xojet smid Categories **LM 04_30_25
    Then user clicks calculate button
    Then user clicks view button
    Then verify Ets fee is not displayed for Xojet
    Examples:
      | depart   | arrive   |  membertype     |
      | "KORD"   | "KMIA"   | "XO MEMBERSHIP" |
      | "KFLL"   | "KJFK"   | "XO MEMBERSHIP" |

  @Calculator
  Scenario Outline: verify ETS fee is $358.00 per leg , OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'etsFee' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax |fee   |
      | "EGGW"   | "LFMN"       | "XO MEMBERSHIP" |  0  |"358" |  
      | "KTEB"   | "EGGW"       | "XO MEMBERSHIP" |  0  |"358" |   
      #Asia
      | "RKSS"   | "RKSI"       | "XO MEMBERSHIP" |  0  |"358" |   



  @Calculator
  Scenario Outline: verify ETS is 358.00 per leg , RT - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'etsFee' fee <pax> <fee>  
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax | fee   |
      | "LIRF"   | "EGGW"   | "EGGW"    | "LIRF"   |"XO MEMBERSHIP" |  0 | "716" | 


#######SEGMENT FEE
  @Calculator
  Scenario Outline: verify Segment fee is $5.00 per leg , OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Segment fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | pax | fee   |
      | "KTEB"   | "KMIA"   | "XO MEMBERSHIP" | 1   |"5.00" |


  @Calculator
  Scenario Outline: verify Segment fee per leg , OW - domestic US/HAWAII
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Segment fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | pax |fee     |
      | "KLAX"   | "PHOG"   | "XO MEMBERSHIP" | 1   |"15.60" |
      
  @Calculator
  Scenario Outline: verify Segment fee , RT - US/HAWAII
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Segment fee <fee> for the domestic categories

    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee  	 |
      | "PHNL"   | "KLAX"   | "KLAX"    | "PHNL"   |"XO MEMBERSHIP" | 1   |"20.80" |
      | "PHOG"   | "KVNY"   | "KVNY"    | "PHOG"   |"XO MEMBERSHIP" | 1   |"20.80" |
  


  @Calculator
  Scenario Outline: verify Segment fee is per leg , OW -  CARIBBEAN/ US
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Segment fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | pax |fee    |
      | "KFLL"   | "TJSJ"   | "XO MEMBERSHIP" | 1   |"5.20" |




  @Calculator
  Scenario Outline: verify there is no segment fee  , OW - international/ Asia
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "EGGW"   | "LFMN"       | "XO MEMBERSHIP" | 1    |"0.00" |
      | "RKSS"   | "RKSI"       | "XO MEMBERSHIP" | 1    |"0.00" |


  @Calculator
  Scenario Outline: verify Segment fee is $0.00 , RT - INT/INT
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee    |
      | "LIRF"   | "LIRA"   | "LIRA"    | "LIRF"   |"XO MEMBERSHIP" | 1   |"0.00" |

	@Calculator
  Scenario Outline: verify Segment fee , OW - HAWAII/CANADA
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "KTEB"   | "LFMN"       | "XO MEMBERSHIP" | 1    |"15.60" |
      
   @Calculator
  Scenario Outline: verify Segment fee , OW - CANADA/US(225 MILES)
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "CYYY"   | "KTEB"       | "XO MEMBERSHIP" | 1    |"10.40" |
      
   @Calculator
  Scenario Outline: verify Segment fee , OW - CANADA/US(NOT IN 225 MILES)
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "CYYT"   | "KTEB"       | "XO MEMBERSHIP" | 1    |"0.00" |
      
  @Calculator
  Scenario Outline: verify Segment fee , OW - US/MEXICO(225 MILES)
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "KLAX"   | "MMAN"       | "XO MEMBERSHIP" | 1    |"10.40" |


   @Calculator
  Scenario Outline: verify Segment fee , OW - US/MEXICO(NOT IN 225 MILES)
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'segmentFee' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "KLAX"   | "MMMX"       | "XO MEMBERSHIP" | 1    |"0.00" |
   



	#######PORTUGAL FEE
  @Calculator
  Scenario Outline: verify fee is $1,300.00 per leg if  any "departure" is from Portugal , OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Portugal fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | pax | fee      |
      | "LPPT"   | "EGGW"   | "XO MEMBERSHIP" | 1 |"1300.00" |
      | "EGGW"   | "LPPT"   | "XO MEMBERSHIP" | 1 |"0.00"    |

  @Calculator
  Scenario Outline: verify fee is $1,300.00 per leg if 'departure" is from Portugal , OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'PortugalFee' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       |pax | fee      | 
      | "LPPT"   | "EGGW"       | "XO MEMBERSHIP" | 0  |"1300.00" | 
      | "LPPT"   | "LPMA"       | "XO MEMBERSHIP" | 0  |"1300.00" | 
      | "EGGW"   | "LPPT"       | "XO MEMBERSHIP" | 0  |"0.00"    | 



  @Calculator
  Scenario Outline: verify fee is $1,300.00 per leg if 'departure" is from Portugal, RT - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'PortugalFee' fee <pax> <fee> 
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax | fee      |  
      | "LPPT"   | "LPMA"   | "LPMA"    | "LPPT"   |"XO MEMBERSHIP" | 0   |"1300.00" |  
      | "EGGW"   | "LPMA"   | "LPMA"    | "EGGW"   |"XO MEMBERSHIP" | 0   |"1300.00" |  
      | "LPPT"   | "EGGW"   | "EGGW"    | "LPPT"   |"XO MEMBERSHIP" | 0   |"1300.00" |  


######WHITE LIST (allow list)
  @Calculator
  Scenario Outline: verify following airports are returning price (whitelist = allow list) , OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user clicks calculate button
    Then user clicks view button
    Then verify whitelist airports for domestic categories
    Examples:
      | depart   | arrive   |  membertype     |
      | "KTEB"   | "KSNA"   | "XO MEMBERSHIP" |
      | "KPWK"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KHPN"   | "KTEB"   | "XO MEMBERSHIP" |
      | "TQPF"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KOXR"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KOAK"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KSUA"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KSDL"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KOTH"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KMMU"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KBNA"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KPIR"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KTUS"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KAPC"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KLOZ"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KCHS"   | "KTEB"   | "XO MEMBERSHIP" |

  @Calculator
  Scenario Outline: verify following airports are returning price for N-Reg-ULR(whitelist = allow list) for nreg-smid , OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user selects aircraft categories <category>
    Then user clicks calculate button
    Then user clicks view button
    Then verify whitelist airports for domestic categories
    Examples:
      | depart   | arrive   |  membertype     | category |
      | "KTEB"   | "KMMU"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KSEZ"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KPGA"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "TQPF"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KCRQ"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KSNA"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KLEB"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |
      | "KOXR"   | "KTEB"   | "XO MEMBERSHIP" |"NREGULR" |

	######OVERNIGHT FEE - CL850
  @Calculator
  Scenario Outline: verify Overnight fee $1,000.00 is displayed per leg for CL850, OW - international
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user selects aircraft categories <category>
    Then user clicks calculate button
    Then user clicks view button
    Then verify overnight fee <fee> for international category
    Examples:
      | depart   | arrive   |  membertype     | category | fee |
      | "EGGW"   | "LTFM"   | "XO MEMBERSHIP" |"CL850"   |"1000.00"|


  @Calculator
  Scenario Outline: verify overnight fee $1,000.00 per leg for CL850, RT - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    Then user selects aircraft categories <category>
    Then user clicks calculate button
    Then user clicks view button
    Then verify overnight fee <fee> for international category
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | category  |fee       |
      | "EGGW"   | "LTFM"   | "LTFM"    | "EGGW"   |"XO MEMBERSHIP" | "CL850"   |"1000.00" |


	######CABOTAGE
  @Calculator
  Scenario Outline: verify cabotage msg is displayed when Domestic route/International categories, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       | pax | fee    | 
      | "KSBO"   | "KTEB"       | "XO MEMBERSHIP" | 0   | "0.00" | 



  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed (but prices displayed) when Domestic route/categories, OW - Domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   | arrive   |  membertype     |
      | "KSBO"   | "KTEB"   | "XO MEMBERSHIP" |

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when NonUk-UK, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax | fee   |  
      | "LIRF"   | "EGGW"       | "XO MEMBERSHIP" | 0   |"0.00" |  



  @Calculator
  Scenario Outline: verify cabotage msg is displayed when UK-UK, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "EGGW"   | "EGKK"       | "XO MEMBERSHIP" | 0   |"0.00" |  


  @Calculator
  Scenario Outline: verify cabotage msg is displayed when UK-UK-NonUK, ML - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee>  
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax  |fee    | 
      | "EGKK"   | "EGGW"   | "EGGW"    | "LIRF"   |"XO MEMBERSHIP" | 0    |"0.00" | 


  @Calculator
  Scenario Outline: verify cabotage msg is displayed when NonUK-UK-UK, ML - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee> 
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee    |  
      | "LIRF"   | "EGGW"   | "EGGW"    | "EGKK"   |"XO MEMBERSHIP" | 0   |"0.00" |  


  @Calculator
  Scenario Outline: verify cabotage msg is displayed when Asia-Asia, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    | 
      | "RJAA"   | "RJBB"       | "XO MEMBERSHIP" | 0    |"0.00" | 


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Australia - Australia, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |  
      | "YMML"   | "YSCB"       | "XO MEMBERSHIP" |  0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Bangladesh - Bangladesh, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "VGHS"   | "VGTJ"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Bosnia Herezegovina - Bosnia Herezgovina, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "LQSA"   | "LQBK"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Cambodia - Cambodia, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "VDPP"   | "VDSA"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Chile - Chile, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax | fee    | 
      | "SCEL"   | "SCVM"       | "XO MEMBERSHIP" |   0  |"0.00" | 

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Laos - Laos, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "VLVT"   | "VLLB"       | "XO MEMBERSHIP" |  0  |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Malaysia - Malaysia, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       | pax |fee    | 
      | "WMKK"   | "WMKA"       | "XO MEMBERSHIP" | 0   | "0.00" | 

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Maldives - Maldives, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "VRMM"   | "VRMG"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Mongolia - Mongolia, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "ZMCK"   | "ZMUB"       | "XO MEMBERSHIP" |   0 |"0.00" | 


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Nigeria - Nigeria, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "DNMM"   | "DNAA"       | "XO MEMBERSHIP" | 0   |"0.00" |  


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Oman - Oman, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "OOTH"   | "OOMS"       | "XO MEMBERSHIP" |  0  |"0.00" |  


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Uzbekistan- Uzbekistan, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "UTTT"   | "UTFA"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> Korea, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "RKSI"   | "RKSS"       | "XO MEMBERSHIP" | 0   |"0.00" |  

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> US Territories, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  | fee    |  
      | "RKSS"   | "KLAX"       | "XO MEMBERSHIP" | 0    |"0.00" | 


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> Europe, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |  
      | "LGAV"   | "RKSS"       | "XO MEMBERSHIP" | 0    |"0.00" | 

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> China, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax |fee    |  
      | "RKSI"   | "VHHH"       | "XO MEMBERSHIP" | 0   |"0.00" |  



  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> Africa, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |  
      | "RKSS"   | "FAOR"       | "XO MEMBERSHIP" | 0    |"0.00" |  


  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea <-> ALL OTHERS, OW - international
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee> 
    Examples:
      | origin   | destination  |membertype       | pax |fee    | 
      | "VRMM"   | "RKSS"       | "XO MEMBERSHIP" | 0   |"0.00" | 
      | "RKSI"   | "HECA"       | "XO MEMBERSHIP" | 0   |"0.00" | 
      | "RKSS"   | "VHHH"       | "XO MEMBERSHIP" | 0   |"0.00" | 

  @Calculator
  Scenario Outline: verify cabotage msg is NOT displayed when Korea - Australia + New Zealand, ML - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'NonCabotage' fee <pax> <fee> 
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee    | 
      | "YSSY"   | "RKSS"   | "RKSS"    | "NZAA"   |"XO MEMBERSHIP" | 0   |"0.00" | 


	######FIRM FEE - XO LITE /XO SMID
 
  #@Calculator  **LILIAN 04_30_25
  #Scenario Outline: verify Firm fee is $2,000.00 per leg for XO SMID, OW - domestic
  #  Given user is on the calculator page
  #  When user enters origin <depart> destination <arrive>
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only XoSmid Category
  #  And user selects Firm Toggle
  #  Then user clicks calculate button
  #  Then user clicks view button
  #  Then verify Firm fee <fee> for the domestic categories
  #  Examples:
  #    | depart   | arrive   |  membertype     | fee       |
  #    | "KTEB"   | "KMIA"   | "XO MEMBERSHIP" | "2000.00" |


  #@Calculator  **LILIAN 04_30_25
  #Scenario Outline: verify Firm fee is $2,000.00 per leg for XO SMID, ROUND TRIP - domestic
  #  Given user is on the calculator page
  #  When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only XoSmid Category
  #  And user selects Firm Toggle
  #  Then user clicks calculate button
  #  Then user clicks view button
   # Then verify Firm fee <fee> for the domestic categories
   # Examples:
   #   | depart   | arrive   | depart2   | arrive2  |membertype      | fee   |
   #   | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "2000.00" |


  @Calculator
  Scenario Outline: verify Firm fee is $750.00 per leg for XO LITE, OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only XoLite Category
    And user selects Firm Toggle
    Then user clicks calculate button
    Then user clicks view button
    Then verify Firm fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | fee       |
      | "KTEB"   | "KMIA"   | "XO MEMBERSHIP" | "750.00" |


  @Calculator
  Scenario Outline: verify Firm fee is $750.00 per leg for XO lite, ROUND TRIP - domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only XoLite Category
    And user selects Firm Toggle
    Then user clicks calculate button
    Then user clicks view button
    Then verify Firm fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee   |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "750.00" |



  @Calculator
  Scenario Outline: verify Firm fee is $0.00 per leg for NREG-SMID and NREG-ULR, OW - domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Firm fee <fee> for the domestic categories
    Examples:
      | depart   | arrive   |  membertype     | fee       |
      | "KTEB"   | "KMIA"   | "XO MEMBERSHIP" | "0.00" |

######SERVICE FEE
	#xo membership
  @Calculator
  Scenario Outline: verify Service fee is $95.00 per trip for Citation Excel only, ROUND TRIP - domestic , XO Membership
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only XoLite Category
    Then user clicks calculate button
    Then user clicks view button
    #LILIAN 07-03-25 begin
    #Then verify Service fee <fee> for the domestic categories trip <trip>
    Then verify Xo Service Charge <fee> for the domestic categories trip <trip>
		Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     | trip  |
     #| "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "95.00" |   1   |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "47.50" |   1   |
    #LILIAN 07-03-25 end

	#xo membership
  @Calculator
  Scenario Outline: verify Service fee is $0.00 per trip for NREGS only, ROUND TRIP - domestic , XO Membership .LILIAN 04_30_25
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    #And select Only XoSmid and Nregs Category  **LILIAN 04_30_25
    Then user clicks calculate button
    Then user clicks view button
    Then verify Service fee <fee> for the domestic categories trip <trip>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee    | trip |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   | "XO MEMBERSHIP"| "0.00" | 1    |

		#select membership
  @Calculator
  Scenario Outline: verify Service fee is $95.00 per trip for Citation Excel only, ROUND TRIP - domestic , Select membership
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    #And select Only XoLite Category for Select_Signature Memberships  **LILIAN 05_07_25
    Then user clicks calculate button
    Then user clicks view button
		#LILIAN 07-03-25 begin
    #Then verify Service fee <fee> for the domestic categories trip <trip>
    Then verify Xo Service Charge <fee> for the domestic categories trip <trip>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     | trip |
     #| "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SELECT"        | "95.00" |   1  |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SELECT"        | "47.50" |   1  |
    #LILIAN 07-03-25 end

	#select membership
	#**LILIAN 04_30_25
  #@Calculator
  #Scenario Outline: verify Service fee is $295.00 per trip for XO SMID (CITATION X) only, ROUND TRIP - domestic , Select membership
  #  Given user is on the calculator page
  #  When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only CitationX Category for Select_Signature Memberships
  #  Then user clicks calculate button
   # Then user clicks view button
  #  Then verify Service fee <fee> for the domestic categories trip <trip>
   # Examples:
   #   | depart   | arrive   | depart2   | arrive2  |membertype      | fee     | trip  |
   #   | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SELECT"        | "295.00"|   1   |

	#Signature membership
	#Signature membership
  #LILIAN 06-24-25 begin
   @Calculator
   Scenario Outline: verify Service fee is $95.00 per trip for Citation Excel only, ROUND TRIP - domestic , Signature membership
   #Scenario Outline: verify Service fee is $95.00 per trip for XO LITE only, ROUND TRIP - domestic , Signature membership
  #LILIAN 06-24-25 end
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    #And select Only XoLite Category for Select_Signature Memberships **LILIAN 05_07_25
    Then user clicks calculate button
    Then user clicks view button
     Then verify Xo Service Charge <fee> for the domestic categories trip <trip>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     |  trip |
     #LILIAN 06-24-25 begin
     #| "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SIGNATURE"        | "95.00" |   1  |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SIGNATURE"        | "47.50" |   1  |
     #LILIAN 06-24-25 end

	#Signature membership
	#**LILIAN 04_30_25
  #@Calculator
  #Scenario Outline: verify Service fee is $295.00 per trip for XO SMID (CITATION X) only, ROUND TRIP - domestic , Signature membership
 #   Given user is on the calculator page
  #  When user enter   s <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only CitationX Category for Select_Signature Memberships
  #  Then user clicks calculate button
  #  Then user clicks view button
  #  Then verify Service fee <fee> for the domestic categories trip <trip>
  #  Examples:
  #    | depart   | arrive   | depart2   | arrive2  |membertype      | fee     | trip |
  #   | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"SIGNATURE"     | "295.00"|   1  |


  ######OTHER AIRCRAFT FEE
  #@Calculator **LILIAN 04_30_25
  #Scenario Outline: verify Other Aircraft fee is $-5,000.00 per leg for XO SMID only, ROUND TRIP - domestic
  #  Given user is on the calculator page
  #  When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only XoSmid Category
   # Then user clicks calculate button
   # Then user clicks view button
   # Then verify Other Aircraft fee <fee>
   # Examples:
   #   | depart   | arrive   | depart2   | arrive2  |membertype      | fee        |
    #  | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "-5000.00" |



  @Calculator
  Scenario Outline: verify Other Aircraft fee is $5,000.00 per leg for NREG-ULR only, ROUND TRIP - domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only NregUrl Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Other Aircraft fee <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "5000.00" |


  @Calculator
  Scenario Outline: verify Other Aircraft fee is $0.00 per leg for XO LITE, ROUND TRIP - domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only XoLite Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Other Aircraft fee <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "0.00"  |


  @Calculator
  Scenario Outline: verify Other Aircraft fee is $0.00 per leg for NREG-SMID only, ROUND TRIP - domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only NregSmid Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Other Aircraft fee <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee     |
      | "KTEB"   | "KFLL"   | "KFLL"    | "KTEB"   |"XO MEMBERSHIP" | "0.00"  |



  @Calculator
  Scenario Outline: verify Other Aircraft fee $-1,000.00 per leg for AH PHENOM300, RT - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only AHPhenom300 Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Other Aircraft fee <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee        |
      | "LIRF"   | "EGGW"   | "EGGW"    | "LIRF"   |"XO MEMBERSHIP" | "-1000.00" |



  @Calculator
  Scenario Outline: verify Other Aircraft fee $-2,000.00 per leg for AH LEGACY600, RT - international
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only AHLegacy600 Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify Other Aircraft fee <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | fee        |
      | "LIRF"   | "EGGW"   | "EGGW"    | "LIRF"   |"XO MEMBERSHIP" | "-2000.00" |

      
      #***LILIAN*** 
  @Calculator
    Scenario Outline: verify max pax number that corresponds to each category - domestic
    Given user is on the calculator page
    When user enters origin "<depart>" destination "<arrive>"
    And verify all categories unselected and select mentioned "<category>" category
    #LILIAN 05_09_25 begin
    Then user clicks calculate button  
    Then user clicks view button				
    #LILIAN 05_09_25 end
    Then verify Max Pax "<maxpax>" Corresponding Category

    Examples:
      | depart | arrive |  category            |maxpax|
      #LILIAN 05_22_25 begin
      #| KTEB   | KIAD   |  N-REG LIGHT JETS    |  7   |
      | KTEB   | KIAD   |  N-REG CIT EXCEL     |  7   |
      #end
      | KTEB   | KIAD   |  VJ N-REG CL300/350  |  8   |
      | KTEB   | KIAD   |  VJ N-REG GIV-SP/450 | 12   |
      #International 
      | EGGW   | LIRF   |   VJ CL350/P600/L500  | 8    |
      | EGGW   | LIRF   |   VJ CL604            | 12   |
      | EGGW   | LIRF   |   VJ CL605            | 12   |
      | EGGW   | LIRF   |   VJ CL850            | 14   |
      | EGGW   | LIRF   |   VJ GLOBAL 6000      | 14   |
      | EGGW   | LIRF   |   VJ GLOBAL 7500      | 14   |                
      | EGGW   | LIRF   |    AH PHENOM 300      | 6    |
      | EGGW   | LIRF   |   AH CITATION XLS     | 7    |
      | EGGW   | LIRF   |   AH LEGACY 600       | 13   |
      | EGGW   | LIRF   |   AH LEGACY 650       | 13   |
      | EGGW   | LIRF   |   AH FALCON 7X        | 12   |     


   @abc1
    Scenario Outline: [PRIC-4423/4421, RM-1809]verify Landing fee for airports in Asia. Categories: CL350,CL605 and GLOBAL6000
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only CL350_CL605_GL6K Category
    Then user clicks calculate button
    Then user clicks view button
    Then user verifies Landing fee for <CL350fee> <CL605fee> <GL6kfee>
    Examples:
      | depart   |arrive    |  CL350fee  |  CL605fee  |  GL6kfee |
      #LILIAN 08-15-25 begin
      | "VHHH"   | "ZGGG"   | "5200.00"  |	"6500.00" | "9000.00" |
      | "VHHH"   | "ZUCK"   | "9000.00"  |	"9000.00" | "9500.00" |
      | "VHHH"   | "ZGSZ"   | "5200.00"  |	"6500.00" | "9000.00" |
      | "VHHH"   | "ZPPP"   | "5600.00"  |	"7900.00" | "8500.00" |
      | "VHHH"   | "ZSHC"   | "5600.00"  |	"7900.00" | "8500.00" |
      | "VHHH"   | "ZUUU"   | "8500.00"  |	"8500.00" | "9500.00" |
      | "VHHH"   | "ZSSS"   | "9000.00"  |	"9800.00" | "11000.00" |
      | "VHHH"   | "ZSPD"   | "8500.00"  |	"1000.00" | "10000.00" |
      | "VHHH"   | "ZBAA"   | "5600.00"  |	"7900.00" | "8600.00" |
      | "VHHH"   | "ZBAD"   | "6800.00"  |	"7000.00" | "7500.00" |
      | "VHHH"   | "ZUTF"   | "7500.00"  |	"7500.00" | "7900.00" |
      | "VHHH"   | "RKSI"   | "4000.00"  |	"4000.00" | "5000.00" |
      | "VHHH"   | "RKSS"   | "5250.00"  |	"6600.00" | "7500.00" |
      | "VHHH"   | "RKPC"   | "4200.00"  |	"4200.00" | "5250.00" |
      | "VHHH"   | "RKPK"   | "4200.00"  |	"4500.00" | "5600.00" |
      | "VHHH"   | "RJTT"   | "9500.00"  |	"9800.00" | "10500.00" |
      | "VHHH"   | "RJCC"   | "7000.00"  |	"9100.00" | "9500.00" |
      | "VHHH"   | "RJFF"   | "4500.00"  |	"4500.00" | "5000.00" |
      | "VHHH"   | "RJAA"   | "6000.00"  |	"7500.00" | "9000.00" |
      | "VHHH"   | "RJOO"   | "5000.00"  |	"5000.00" | "6000.00" |
      | "VHHH"   | "RJBB"   | "5500.00"  |	"6000.00" | "7900.00" |

      
    @abc1
    Scenario Outline:[PRIC-4423/4421,RM-1809]Any airport not listed above for China,Japan or South Korea should be following the default prices listed. Categories: CL350,CL605 and GLOBAL6000
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only CL350_CL605_GL6K Category
    Then user clicks calculate button
    Then user clicks view button
    Then user verifies Landing fee for <CL350fee> <CL605fee> <GL6kfee>
    Examples:
      | depart   |arrive    |  CL350fee  |  CL605fee  |  GL6kfee |
      #CHINA
      | "VHHH"   | "ZSJH"   | "5250.00"  |	"6600.00" | "7500.00" |
      | "VHHH"   | "ZSOF"   | "5250.00"  |	"6600.00" | "7500.00" |
      | "VHHH"   | "ZSTX"   | "5250.00"  |	"6600.00" | "7500.00" |
        #JAPAN
       | "VHHH"   | "RJSK"   | "5250.00"  |	"6600.00" | "7500.00" |
       | "VHHH"   | "RJKA"   | "5250.00"  |	"6600.00" | "7500.00" |
       | "VHHH"   | "RJSA"   | "5250.00"  |	"6600.00" | "7500.00" |
       | "VHHH"   | "RJEC"   | "5250.00"  |	"6600.00" | "7500.00" |
       | "VHHH"   | "RJTA"   | "5250.00"  |	"6600.00" | "7500.00" |
       #SOUTH KOREA
			 | "VHHH"   | "RKTU"   | "5250.00"  |	"6600.00" | "7500.00" |
			 | "VHHH"   | "RKPU"   | "5250.00"  |	"6600.00" | "7500.00" |
			 | "VHHH"   | "RKNY"   | "5250.00"  |	"6600.00" | "7500.00" |
			 | "VHHH"   | "RKTN"   | "5250.00"  |	"6600.00" | "7500.00" |
			 | "VHHH"   | "RKJJ"   | "5250.00"  |	"6600.00" | "7500.00" |
      #LILIAN 08-15-25 end
      
    @Calculator 
    Scenario Outline: verify Minimum Runway/Insufficient Runway msg for domestic categories  (Ravinder will implement more routes using database- PRIC-4471)
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user clicks calculate button
    Then user clicks view button
    Then verify minimum Runway message
    Examples:
      | depart   | arrive   |  membertype     |
      | "KMGC"   | "KMIA"   | "XO MEMBERSHIP" |
      | "KAKH"   | "KEYW"   | "XO MEMBERSHIP" |
      | "KSPH"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KAXA"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KAJO"   | "KLAX"   | "XO MEMBERSHIP" |
      | "KRIU"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KOOA"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KLUD"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KVTI"   | "KTEB"   | "XO MEMBERSHIP" |
      
    @Calculator 
    Scenario Outline: verify Minimum Runway/Insufficient Runway msg for international categories    #(Ravinder will implement more routes using database- PRIC-4471)
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'MinRunway' fee <pax> <fee>
    Examples:
      | depart   |arrive    |  membertype    |pax |fee   |
      | "LBBO"   | "LGAV"   | "XO MEMBERSHIP"|  1 |"0.00" |
      | "EIIR"   | "LTFM"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EGGW"   | "LOGO"   | "XO MEMBERSHIP"|  1 |"0.00" |
      | "EGGW"   | "LFAE"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EGGW"   | "LSTA"   | "XO MEMBERSHIP"|  1 |"0.00" |
      | "EDNR"   | "LGAV"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EGFP"   | "LGAV"   | "XO MEMBERSHIP"|  1 |"0.00" |
      | "VAJJ"   | "LGAV"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EBAM"   | "LIRF"   | "XO MEMBERSHIP"|  1 |"0.00" |
      | "LEMU"   | "KTEB"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EDBT"   | "LIRF"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "LOWZ"   | "LGAV"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "LPVL"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "SDZG"   | "KMIA"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "BGAA"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "FLKC"   | "LGAV"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "LBBO"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "CYXP"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "CZNL"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "SNST"   | "KTEB"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "YLAO"   | "NZAA"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "EGHO"   | "LIRF"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "SCIR"   | "SABE"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "CYSH"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "OPWN"   | "OMDB"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "BITN"   | "EGGW"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "YLGU"   | "NZAA"   |"XO MEMBERSHIP" |  1 |"0.00" |
      | "YBRL"   | "NZAA"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "LQBI"   | "OMDB"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SWGP"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "LJBO"   | "EGGW"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SCFT"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SSPF"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "LFEJ"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "LFDK"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "EPOD"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SNJW"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SDUD"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "YCBG"   | "NZAA"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "EDFZ"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "EFKO"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "EGTU"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "EGBW"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "PAWB"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "LOLU"   | "LIRF"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SWCD"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "AYCH"   | "LGAV"   |"XO MEMBERSHIP" | 1 |"0.00" |
      | "SIBD"   | "SABE"   |"XO MEMBERSHIP" | 1 |"0.00" |
      #Asia
  		| "NGTE"   | "RJAA"   |"XO MEMBERSHIP" | 1 |"0.00" |
  		| "LPVL"   | "RJAA"   |"XO MEMBERSHIP" | 1 |"0.00" |
  		| "WIOG"   | "VHHH"   |"XO MEMBERSHIP" | 1 |"0.00" |
  		| "RPVS"   | "VHHH"   |"XO MEMBERSHIP" | 1 |"0.00" |

    @Calculator
    Scenario Outline: [PRIC-4443]verify "Maldives Passenger Tax" is $480.00 per pax Departure- MAX PAX
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
     And user selects <pax> number
    Then user selects international categories and verifies 'MaldivesPassengerTax' fee <pax> <fee> 
    Examples:
      | depart |  arrive  |  membertype     | pax |fee       |    
			| "VRDA" | "VOML"   | "XO MEMBERSHIP" | 1   | "480.00" |
			| "VRDA" | "VOML"   | "XO MEMBERSHIP" | 5   | "480.00" |
      
   @Calculator
    Scenario Outline: [PRIC-4443]verify "Maldives Passenger Tax" is $480.00 per pax Departure- OW
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'MaldivesPassengerTax' fee <pax> <fee> 
    Examples:
      | depart |  arrive  |  membertype     | pax  |fee       |    
      | "VRDA" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" | 
      | "VRMG" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
  		| "VRMM" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
  		| "VRMO" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
  		| "VRMU" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
  		| "VRMV" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
  		
  @Calculator
  Scenario Outline: [PRIC-4443]verify "Maldives Passenger Tax" is $480.00 per pax Departure-  ROUND TRIP
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'MaldivesPassengerTax' fee <pax> <fee> 
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee      |
      | "VRDA"   | "VOML"   | "VOML"    | "VRDA"   |"XO MEMBERSHIP" | 1 |"480.00" |
      | "VRDA"   | "VRMM"   | "VRMM"    | "VRDA"   |"XO MEMBERSHIP" | 1 |"960.00" |
  		
   @Calculator
    Scenario Outline: [PRIC-4445]verify "Maldives-VRMA Airport Development Tax", $480 per pax Departure- OW
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'VRMMAirportDevelopmentTax' fee <pax> <fee>
    Examples:
      | depart |  arrive  |  membertype     | pax  |fee       |    
			| "VRMM" | "VOML"   | "XO MEMBERSHIP" | 1  | "480.00" |
			
	@Calculator
    Scenario Outline: [PRIC-4445]verify "Maldives -VRMA Airport Development Tax" is $0.00 - OW, Negative test
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'VRMMAirportDevelopmentTax' fee <pax> <fee>
    Examples:
      | depart |  arrive  |  membertype     | pax  |fee       |    
			| "VOML" | "VRMM"   | "XO MEMBERSHIP" | 1  | "0.00"   |
			
	@Calculator
  Scenario Outline: [PRIC-4445]verify "Maldives-VRMM Airport Development Tax", $480 per pax Departure-   ROUND TRIP
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'MaldivesPassengerTax' fee <pax> <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee      |
      | "VRMM"   | "VOML"   | "VOML"    | "VRMM"   |"XO MEMBERSHIP" | 1 |"480.00" |
      

   @Calculator
    Scenario Outline: [PRIC-4444]verify "Maldives- "VRDA Vip Lounge= $75.00" For "Departures and Arrivals"- OW
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'VRDA Vip Lounge' fee <pax> <fee>
    Examples:
      | depart |  arrive  |  membertype     | pax  | fee   | 
      | "VOML" | "VRDA"   | "XO MEMBERSHIP" | 1  | "75.00"|   
			| "VRDA" | "VOML"   | "XO MEMBERSHIP" | 1  | "75.00"|

			
	@Calculator
  Scenario Outline: [PRIC-4444]verify "Maldives- "VRDA Vip Lounge= $75.00" For "Departures and Arrivals"-   ROUND TRIP
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'VRDA Vip Lounge' fee <pax> <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee    |
      | "VRDA"   | "VOML"   | "VOML"    | "VRDA"   |"XO MEMBERSHIP" | 1 |"75.00" |
			
			
    @Calculator
    Scenario Outline: [PRIC-4444]verify "Maldives- "VRMM Vip Lounge= $219.07"  for "Departures and Arrivals"- OW
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'VRMM Vip Lounge' fee <pax> <fee>
    Examples:
      | depart |  arrive  |  membertype     | pax  | fee   |  
			| "VRMM" | "VOML"   | "XO MEMBERSHIP" | 1  | "219.07"| 
			| "VOML" | "VRMM"   | "XO MEMBERSHIP" | 1  | "219.07"| 
	  	
	@Calculator
  Scenario Outline: [PRIC-4444]verify "Maldives- "VRMM Vip Lounge= $219.07"  for "Departures and Arrivals"-   ROUND TRIP
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
     Then user selects international categories and verifies 'VRMM Vip Lounge' fee <pax> <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax |fee     |
      | "VRMM"   | "VOML"   | "VOML"    | "VRMM"   |"XO MEMBERSHIP" | 1 |"219.07" |

      
   #***LILIAN*** 

   #**LILIAN 05_20_25 begin
    @Calculator
    Scenario Outline: verify invalid max pax number = 20  - domestic
    Given user is on the calculator page
    When user enters origin "<depart>" destination "<arrive>"
    And verify all categories unselected and select mentioned "<category>" category
    And user selects <pax> number     
    Then user clicks calculate button  
    Then user clicks view button				
    Then verify Exceeded Max Pax Limit message "<category>" <message>
		Examples:
      | depart | arrive |category             |pax  | message                                          |
      #LILIAN 05_22_25
      #| KTEB   | KIAD   | N-REG LIGHT JETS    |20   | " Exceeded max pax limit: 7, requested: 20 pax " |
      | KTEB   | KIAD   | N-REG CIT EXCEL     |20   | " Exceeded max pax limit: 7, requested: 20 pax " |
      #end
      | KTEB   | KIAD   | VJ N-REG CL300/350  |20   | " Exceeded max pax limit: 8, requested: 20 pax " |
      | KTEB   | KIAD   | VJ N-REG GIV-SP/450 |20   | " Exceeded max pax limit: 12, requested: 20 pax " |
      
    @Calculator
    Scenario Outline: verify dropdown pax matches with returned pax
    Given user is on the calculator page
    When user enters origin "<depart>" destination "<arrive>"
    And user selects <pax> number     
    Then user clicks calculate button  
    Then user clicks view button				
    Then verify pax in dropdown <pax> matches returned pax for domestic categories
		Examples:
      | depart | arrive |pax  |
      | KTEB   | KFLL   | 1   | 
      
#LILIAN 05_20_25 end

#LILIAN 06-04-25 begin
 @Calculator
 #LILIAN 07-03-25 begin
  #Scenario Outline: verify ORIGIN AIRPORT FEES with UI and MongoDB for CITATION EXCEL ONLY
  Scenario Outline: verify RWA Premium Depart fees with UI and MongoDB for CITATION EXCEL ONLY

    Given user is on the calculator page
    When user enters origin "<origin>" destination "<destination>"
    And verify all categories unselected and select mentioned "<category>" category
    Then user clicks calculate button  
    Then user clicks view button	
    #Then verify Origin Destination Airports with UI and MongoDB "<origin>" "<test>"
    Then verify Origin Destination Airports with UI and MongoDB "<departure>" "<test>"
   
		Examples:
      | origin | destination |  category 			 | test     |
      | KASE   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KDEN   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KEGE   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KAPF   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KBCT   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KEYW   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KMIA   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KOPF   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KPBI   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KATL   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KPDK   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KSAV   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KORD   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KMDW   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KDPA   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KUGN   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KPWK   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KHHF   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KINK   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KPRS   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KTDW   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KCVG   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KBOS   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KBED   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KHYA   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KMVY   | KTEB  	     |  N-REG CIT EXCEL|departuretest|
      | KDTW   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KIMT   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KMNM   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KOSC   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KPLN   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KSAW   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KTVC   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KINL   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KMSP   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KRGK   | KTEB        |  N-REG CIT EXCEL|departuretest|
      | KRRT   | KTEB        |  N-REG CIT EXCEL|departuretest|

	 #LILIAN 07-03-25 end
	
	@Calculator
	#LILIAN 07-03-25 begin
  #Scenario Outline: verify DESTINATION AIRPORT FEES with UI and MongoDB for CITATION EXCEL ONLY
  Scenario Outline: verify RWA Premium Arrive with UI and MongoDB for CITATION EXCEL ONLY
  #LILIAN 07-03-25 end
    Given user is on the calculator page
    When user enters origin "<origin>" destination "<destination>"
    And verify all categories unselected and select mentioned "<category>" category
    Then user clicks calculate button  
    Then user clicks view button	
    #Then verify Origin Destination Airports with UI and MongoDB "<destination>" "<test>"
    Then verify Origin Destination Airports with UI and MongoDB "<arrive>" "<test>"
   Examples:
      | origin | destination |  category 			   | test          |
      | KTEB   | KDEN        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KEGE        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KAPF        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KBCT        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KEYW        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KMIA        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KOPF        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KPBI        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KVRB        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KATL        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KPDK        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KSAV        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KORD        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KMDW        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KDPA        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KUGN        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KPWK        |  N-REG CIT EXCEL  |arrivetest|
			| KTEB   | KHHF        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KINK        |  N-REG CIT EXCEL  |arrivetest|
			| KTEB   | KPRS        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KTDW        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KCVG        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KBOS        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KBED        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KHYA        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KMVY        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KDTW        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KIMT        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KMNM        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KOSC        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KPLN        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KSAW        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KTVC        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KINL        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KMSP        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KRGK        |  N-REG CIT EXCEL  |arrivetest|
      | KTEB   | KRRT        |  N-REG CIT EXCEL  |arrivetest|
 #LILIAN 07-03-25 end
 

	@Calculator
	Scenario Outline:[PRIC-4776, RM-1839]Citation Excel-Verify UI base price matches with State to State Excel base price #LILIAN 08-03-25 
  Scenario Outline: Citation Excel - Verify UI base price matches with State to State Excel base price #LILIAN 08-03-25 
    Given user is on the calculator page
    When user enters origin "<origin>" destination "<destination>"
    And verify all categories unselected and select mentioned "<category>" category
    Then user clicks calculate button  
    Then user clicks view button	
    Then verify UI matches State to State Excel base price <baseprice> 
    Examples:
    | origin | destination |  category 			   | baseprice  |
     #FL-NY
    | KMIA   | KJFK        |   N-REG CIT EXCEL | "18500.00" |
    #NY-FL
    | KJFK   | KFLL        |   N-REG CIT EXCEL | "18500.00" |
    #FL-NJ
    | KMIA   | KTEB        |   N-REG CIT EXCEL | "18000.00" |
    #NJ-FL
    | KTEB   | KFLL       |   N-REG CIT EXCEL | "18000.00" |
    #FL-MA
    | KMIA   |   KBOS    |   N-REG CIT EXCEL | "18800.00" |
    #MA-FL
    | KBOS   | KMIA        |   N-REG CIT EXCEL | "18800.00" |
    #FL-VA
    | KMIA   |   KRIC    |   N-REG CIT EXCEL | "16000.00" |
    #VA-FL
    | KRIC   |   KFLL    |   N-REG CIT EXCEL | "16000.00" |
    #FL-IL
    | KMIA   | KORD       |   N-REG CIT EXCEL | "20800.00" |
    #IL-FL
    | KORD   | KFLL       |   N-REG CIT EXCEL | "20800.00" |
  	 #FL-SC
    | KMIA   | KCHS       |   N-REG CIT EXCEL | "13600.00" |
     #SC-FL
    | KCHS   | KFLL       |   N-REG CIT EXCEL | "13600.00" | 
    #FL-PA
    | KMIA   | KPHL       |   N-REG CIT EXCEL | "17200.00" |
    #PA-FL
    | KPHL   | KMIA       |   N-REG CIT EXCEL | "17200.00" |
    #FL-KS 
    | KMIA   | KICT        |   N-REG CIT EXCEL | "24000.00" |
    #KS -FL
    | KICT   | KMIA        |   N-REG CIT EXCEL | "24000.00" |
    #FL-LA 
    | KMIA   | KMSY        |   N-REG CIT EXCEL | "18500.00" |
    #LA -FL
    | KMSY   | KFLL        |   N-REG CIT EXCEL | "18500.00" |
    #FL-ND 
    | KMIA   | KGFK        |   N-REG CIT EXCEL | "28000.00" |
    #ND-FL
    | KGFK   | KFLL        |   N-REG CIT EXCEL | "28000.00" |
    #FL-NE 
    | KMIA   | KOMA        |   N-REG CIT EXCEL | "23600.00" |
    #NE -FL
    | KOMA   | KFLL        |   N-REG CIT EXCEL | "23600.00" |
    #FL-OK 
   | KMIA   | KOKC       |   N-REG CIT EXCEL | "22200.00" |
     #OK-FL
    | KOKC   | KFLL       |   N-REG CIT EXCEL | "22200.00" |
    #FL-MD
    | KMIA   | KBWI       |   N-REG CIT EXCEL | "16400.00" |
    #MD-FL
    | KBWI   | KMIA       |   N-REG CIT EXCEL | "16400.00" |
    #FL-MI
    | KMIA   | KDTW        |   N-REG CIT EXCEL | "21200.00" |
  	#MI-FL
    | KDTW   | KFLL       |   N-REG CIT EXCEL | "21200.00" | 
    #FL-FL
    | KMIA   | KJAX        |   N-REG CIT EXCEL | "12600.00" |
 		#FL-OH
    | KMIA   | KCLE        |   N-REG CIT EXCEL | "17600.00" |
     #OH-FL
    | KCMH   | KMIA        |   N-REG CIT EXCEL | "17600.00" |  
    #FL-TN 
    | KMIA   | KBNA        |   N-REG CIT EXCEL | "20200.00" |
    #TN-FL
    | KBNA   | KMIA        |   N-REG CIT EXCEL | "20200.00" |
    #FL-GA
    | KMIA   | KATL        |   N-REG CIT EXCEL | "15600.00" |
     #GA-FL
    | KATL   | KFLL        |   N-REG CIT EXCEL | "15600.00" |
    #FL-RI
    | KMIA   | KPVD        |   N-REG CIT EXCEL | "18800.00" |
    #RI-FL
    | KPVD   | KFLL        |   N-REG CIT EXCEL | "18800.00" |
    #FL-TX 
    | KMIA   | KDFW       |   N-REG CIT EXCEL | "22400.00" |
    #TX-FL 
    | KDFW   | KFLL       |   N-REG CIT EXCEL | "22400.00" |
    #FL-ME 
    | KMIA   | KPWM        |   N-REG CIT EXCEL | "20000.00" | 
    #ME -FL
    | KPWM   | KMIA        |   N-REG CIT EXCEL | "20000.00" | 
     #FL-WI 
    | KMIA   | KMKE        |   N-REG CIT EXCEL | "22400.00" |
    #WI-FL 
    | KMKE   | KMIA        |   N-REG CIT EXCEL | "22400.00" |
   	#FL-IN 
    | KMIA   | KIND        |   N-REG CIT EXCEL | "18000.00" |
    #FL-IN 
   	| KIND   | KFLL        |   N-REG CIT EXCEL | "18000.00" |
 		#FL-MO 
    | KMIA   | KSTL       |   N-REG CIT EXCEL | "18000.00" |
    #MO-FL 
    | KSTL   | KMIA       |   N-REG CIT EXCEL | "18000.00" |
    #FL-KY 
    | KMIA   | KCVG       |   N-REG CIT EXCEL | "20200.00" |
		#KY-FL 
    | KCVG   | KFLL       |   N-REG CIT EXCEL | "20200.00" |
    
    #AL-FL
    | KBHM   | KFLL        |   N-REG CIT EXCEL | "16000.00" |
    #AL-AR
    | KBHM   | KXNA        |   N-REG CIT EXCEL | "17000.00" |
    #AL-CO
    | KBHM   | KCOS        |   N-REG CIT EXCEL | "25400.00" |
    #AL-MO
    | KBHM   | KSTL        |   N-REG CIT EXCEL | "15800.00" |
    #AL-RI
    | KBHM   | KPVD        |   N-REG CIT EXCEL | "20400.00" |
    #AL-WI
    | KBHM   | KMKE        |   N-REG CIT EXCEL | "19600.00" | 
		
		#AR-OH
    | KXNA   | KCLE        |   N-REG CIT EXCEL | "17800.00" | 
     #AR-NH
    | KXNA   | KPSM        |   N-REG CIT EXCEL | "23400.00" | 
     #AR-MN
    | KXNA   | KMSP        |   N-REG CIT EXCEL | "17000.00" |
    
    #AZ-MN
    | KXNA   | KMSP        |   N-REG CIT EXCEL | "17000.00" | 
    
    #CO-FL
    | KDEN   | KMIA        |   N-REG CIT EXCEL | "26400.00" | 
    #CO-WI
    | KGJT   | KGRB        |   N-REG CIT EXCEL | "21600.00" |  
    #CO-GA
    | KDEN   | KATL        |   N-REG CIT EXCEL | "24000.00" |    
    #CO-MI 
    | KCOS   | KDTW        |   N-REG CIT EXCEL | "24400.00" | 
  
  	#CT-OK
    | KBDL   | KOKC        |   N-REG CIT EXCEL | "23000.00" |
    #CT-VT
    | KBDL   | KIAD        |   N-REG CIT EXCEL | "12000.00" |
    #CT-VT
    | KBDL   | KLEX        |   N-REG CIT EXCEL | "18600.00" |
   
    #LILIAN 07-16-25 begin
    #VA-LA
    #| KDCA   | KBTR       |   N-REG CIT EXCEL | "27200.00" |
    | KDCA   | KBTR       |   N-REG CIT EXCEL | "20700.00" |
    
    #VA-NJ
    #| KDCA   | KTEB       |   N-REG CIT EXCEL | "12600.00" | 
    | KDCA   | KTEB       |   N-REG CIT EXCEL | "12000.00" | 
      
     #VA-SC
    | KDCA   | KCHS       |   N-REG CIT EXCEL | "12000.00" |   
    
     #VA-ME   
    | KIAD   | KPWM        |   N-REG CIT EXCEL | "14400.00" |
    #LILIAN 07-16-25 end
    
    
    #DE-WV
    | KILG  | KCKB       |   N-REG CIT EXCEL | "12200.00" | 
     #DE-SD 
    | KILG   | KRAP        |   N-REG CIT EXCEL | "22000.00" | 

  	#GA-OH
    | KATL   | KCMH         |   N-REG CIT EXCEL | "13600.00" |
     #GA-VT
    | KATL   | KRUT        |   N-REG CIT EXCEL | "18400.00" |
    
    #IA-SD 
    | KDSM   | KRAP        |   N-REG CIT EXCEL | "16000.00" |
    #IA-NJ
    | KDSM   | KTEB        |   N-REG CIT EXCEL | "19600.00" |
    
    #IL-MA
    | KCMI   | KBOS        |   N-REG CIT EXCEL | "18000.00" |
    #MA-IL
    | KBOS   | KCMI        |   N-REG CIT EXCEL | "18000.00" |
    
    #IL-SD
    | KCMI   | KRAP        |   N-REG CIT EXCEL | "17600.00" |
     #IL-IL 
    | KCMI   | KORD        |   N-REG CIT EXCEL | "12000.00" |  
    
    #IN-IL 
    | KCMI   | KORD        |   N-REG CIT EXCEL | "12000.00" | 
     #IN-NY 
    | KCMI   | KJFK        |   N-REG CIT EXCEL | "16400.00" |  
    
    #KS-RI
    | KICT   | KJFK        |   N-REG CIT EXCEL | "23400.00" |  
    
    #KY-NH
    | KLEX   | KPSM       |   N-REG CIT EXCEL | "19000.00" |
    
    #LA-NH
    | KSHV   | KPSM       |   N-REG CIT EXCEL | "22300.00" |  
   	
   	#MA-NJ
    | KBOS   | KTEB       |   N-REG CIT EXCEL | "12000.00" |  
    
    #MD-NY
    | KBWI   | KJFK       |   N-REG CIT EXCEL | "12000.00" |  
    
    #ME-NY
    | KBWI   | KJFK       |   N-REG CIT EXCEL | "12000.00" | 
    #MI-NY
    | KAZO  | KJFK       |   N-REG CIT EXCEL | "16600.00" | 
    #MN-NY
    | KBRD  | KJFK       |   N-REG CIT EXCEL | "20400.00" | 
    #MO-NY
    | KJLN  | KJFK       |   N-REG CIT EXCEL | "19800.00" |  
    #MS-NY
    | KTUP  | KJFK       |   N-REG CIT EXCEL | "20200.00" |    
    #NC-NJ
    | KCLT  | KTEB       |   N-REG CIT EXCEL | "14400.00" |  
    #ND-NJ
    | KMOT  | KTEB       |   N-REG CIT EXCEL | "23700.00" |  
    #NE-NJ
    | KOMA  | KTEB       |   N-REG CIT EXCEL | "22000.00" |  
   #NH-NJ
    | KMHT  | KTEB       |   N-REG CIT EXCEL | "12000.00" |  
   #NJ-WI
    | KTEB  | KMKE       |   N-REG CIT EXCEL | "17800.00" | 
    #NY-TX
    | KJFK  | KDFW       |   N-REG CIT EXCEL | "26000.00" | 
    #OH- TX
    | KCLE  | KDFW       |   N-REG CIT EXCEL | "21600.00" | 
    #OK -TX
    | KOKC  | KDFW       |   N-REG CIT EXCEL | "20000.00" |    
    #PA-TX
    | KPHL  | KDFW       |   N-REG CIT EXCEL | "24400.00" | 
    #RI-TX
    | KPVD  | KDFW       |   N-REG CIT EXCEL | "25000.00" | 
    #SC-TX   
    | KCHS   | KAMA        |   N-REG CIT EXCEL | "20400.00" |
    #SD-TX
    | KFSD   | KAMA        |   N-REG CIT EXCEL | "22500.00" |
    #TN-TX
    | KBNA   | KAMA        |   N-REG CIT EXCEL | "21400.00" |
    #TX-TX   
    | KAMA   | KDFW        |   N-REG CIT EXCEL | "20000.00" |  
    #TX-SC 
    | KAMA   | KCHS        |   N-REG CIT EXCEL | "20400.00" | 
   #VA-WI
    | KDCA   | KMKE        |   N-REG CIT EXCEL | "15200.00" | 
    #VT-WI
    | KRIC   | KMKE        |   N-REG CIT EXCEL | "15200.00" | 
    #WV -WV
    | KCRW   | KMGW        |   N-REG CIT EXCEL | "12000.00" | 
    

    
 	@Calculator
  Scenario Outline: Citation Excel - Verify State to State Excel base price = $99,999.99
    Given user is on the calculator page
    When user enters origin "<origin>" destination "<destination>"
    And verify all categories unselected and select mentioned "<category>" category
    Then user clicks calculate button  
    Then user clicks view button	
    Then verify State to State Excel base price is not covered <total> 
    Examples:
    | origin | destination |  category 			  | total        |    
    #ID-FL
    | KBOI   | KFLL        |   N-REG CIT EXCEL | "0.00"      |
   	#AZ-OH
    | KPHX   | KCMH        |   N-REG CIT EXCEL | "0.00"       |
   	#NV-VT   
    | KLAS   | KBTV        |   N-REG CIT EXCEL | "0.00"       | 
   	#AR-NV   
    | KBHM   | KLAS        |   N-REG CIT EXCEL | "0.00"       | 
    #AZ-FL  
    | KIWA   | KMIA        |   N-REG CIT EXCEL | "0.00"       |
   	#CA-NV 
    | KLAX   | KATL        |   N-REG CIT EXCEL | "0.00"       |
    #DC-CA    
    | KDCA   | KLAX        |   N-REG CIT EXCEL | "0.00"       |  
    #MT-NJ 
    | KMSO   | KJFK        |   N-REG CIT EXCEL | "0.00"       |  
    #NM-NY 
    | KTEB   | KABQ        |   N-REG CIT EXCEL | "0.00"       | 
#LILIAN 06-04-25 end


#LILIAN 06-26-25 begin
   @Calculator
    Scenario Outline: [PRIC-2223, RM-1010]verify US-extended service area:return  price for 604 and 605 categories
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only CL604 and CL605 categories
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | membertype     |
      | "KTEB"   | "MYNN"   |"XO MEMBERSHIP" |
      | "LEMD"   | "LEBL"   |"XO MEMBERSHIP" |
      | "KTEB"   | "KFLL"   |"XO MEMBERSHIP" |
  		| "EGGW"   | "LFMN"   |"XO MEMBERSHIP" |
  		| "KTEB"   | "CYYZ"   |"XO MEMBERSHIP" |
  		| "MMSD"   | "PHNL"   |"XO MEMBERSHIP" |
  		| "KTEB"   | "KSBO"   |"XO MEMBERSHIP" |
			| "MYAM"   | "KTEB"   |"XO MEMBERSHIP" |
			| "MYAT"   | "KMIA"   |"XO MEMBERSHIP" |
			
		@Calculator
    Scenario Outline: [PRIC-4505, RM-1822]Add Kyrgyzstan to the Legacy 650 -Calculator only
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AH L650 category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | membertype     |
      | "UCFL"   | "UTDD"   |"XO MEMBERSHIP" |
      | "UCFM"   | "UTDD"   |"XO MEMBERSHIP" |
      | "UCFO"   | "UTDD"   |"XO MEMBERSHIP" |
      | "UCFP"   | "UTDD"   |"XO MEMBERSHIP" |
      | "UAFW"   | "UTDD"   |"XO MEMBERSHIP" |
    
    @Calculator
    Scenario Outline: [PRIC-4085, RM-1755] Add Support for OERS[Hanak, Saudi Arabia] airport for Pricing for all Fleets
    Given user is on the calculator page
  	When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'OERSsupport' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "OERS"   | "LFMN"       | "XO MEMBERSHIP" | 1    |"0.00" |
      | "LIRF"   | "OERS"       | "XO MEMBERSHIP" | 1    |"0.00" |
      
    @Calculator
    Scenario Outline: [PRIC-3797, RM-1697]Super Light changes for eft less than 5 hours-return price
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | pax | membertype     |
      | "EGGW"   | "LGAV"   |  1  |"XO MEMBERSHIP" |
      
    
    @Calculator
    Scenario Outline: [PRIC-3797, RM-1697]Super Light changes for eft for 5 hours- return message
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Flight Time Outside The Aircraft Range Message <depart> <message>
    Examples:
      | depart   |arrive    | membertype     | pax | message 																								|
      | "EGGW"   | "OEKM"   |"XO MEMBERSHIP" | 1   |"Flight Time (7.33h) Outside The Aircraft Range (5.00h)"|
      | "EGKK"   | "OSDI"   |"XO MEMBERSHIP" | 1   |"Flight Time (5.17h) Outside The Aircraft Range (5.00h)"|
      | "LEMD"   | "OMDB"   |"XO MEMBERSHIP" | 1   |"Flight Time (7.83h) Outside The Aircraft Range (5.00h)"|
      | "LFMN"   | "OMDB"   |"XO MEMBERSHIP" | 1   |"Flight Time (6.67h) Outside The Aircraft Range (5.00h)"|
      


  @Calculator
    Scenario Outline: [PRIC-3719, RM-1685]AH L650 display msg once eft > 15 hrs
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AHLegacy650 Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
     Then verify Flight Time Outside The Aircraft Range Message <depart> <message>
    Examples:
      | depart   |arrive    | membertype     | pax |message 																									|
      | "KTEB"   | "VOCP"   |"XO MEMBERSHIP" | 1   |"Flight Time (17.50h) Outside The Aircraft Range (15.00h)" |
     
  @Calculator
    Scenario Outline: [PRIC-3719, RM-1685]AH L650 return price once eft < 15 hrs
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AHLegacy650 Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | pax | membertype     |
      | "KLAX"   | "LGAV"   |  1  |"XO MEMBERSHIP" |
  
  
  
  @Calculator
  Scenario Outline: [PRIC-3854, RM-1710] Ban all trips between Cyprus and Turkey
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'TurkeyCyprusBanned' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "LCLK"   | "LTFJ"       | "XO MEMBERSHIP" | 0    |"0.00" |
      | "LTBJ"   | "LCPH"       | "XO MEMBERSHIP" | 0    |"0.00" |
      | "LTCF"   | "LCLK"       | "XO MEMBERSHIP" | 0    |"0.00" |
  
  @Calculator
  Scenario Outline: [PRIC-3854, RM-1710] Return price When Cyprus-Cyprus  [Negative test]
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'CyprusToCyprus' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |
      | "LCLK"   | "LCPH"       | "XO MEMBERSHIP" | 0    |"0.00" |
      | "LCPH"   | "LCLK"       | "XO MEMBERSHIP" | 0    |"0.00" |
  
  @Calculator
  Scenario Outline: [PRIC-3854, RM-1710] verify cabotage msg is displayed When Turkey-Turkey
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'Cabotage' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax | fee    |
      | "LTAC"   | "LTFM"       | "XO MEMBERSHIP" | 0   | "0.00" |
      | "LTFM"   | "LTAC"       | "XO MEMBERSHIP" | 0   | "0.00" |
      | "LTAI"   | "LTFJ"       | "XO MEMBERSHIP" | 0   | "0.00" |
      | "LTFJ"   | "LTAI"       | "XO MEMBERSHIP" | 0   | "0.00" |
      | "LTBJ"   | "LTBS"       | "XO MEMBERSHIP" | 0   | "0.00" |
      | "LTAF"   | "LTAC"       | "XO MEMBERSHIP" | 0   | "0.00" |
		
		
		@Calculator
    Scenario Outline: [PRIC-3806, RM-1703] Legacy 650 should allow US trips
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AH L650 category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | membertype     |
      | "EGGW"   | "KTEB"   |"XO MEMBERSHIP" |
      | "KTEB"   | "CYYZ"   |"XO MEMBERSHIP" |
      | "EGGW"   | "TJSJ"   |"XO MEMBERSHIP" |
      | "KTEB"   | "CYBB"   |"XO MEMBERSHIP" |
      | "MYAM"   | "KTEB"   |"XO MEMBERSHIP" |
   
    @Calculator
    Scenario Outline: [PRIC-3806, RM-1703] Legacy 600 should Not allow US trips
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AH L600 category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns Out of Service Area for AH LEGACY 600 message
    Examples:
      | depart   |arrive    | membertype     |
      | "EGGW"   | "KTEB"   |"XO MEMBERSHIP" |
      | "KTEB"   | "CYYZ"   |"XO MEMBERSHIP" |
      | "EGGW"   | "TJSJ"   |"XO MEMBERSHIP" |
      | "KTEB"   | "CYBB"   |"XO MEMBERSHIP" |
      | "MYAM"   | "KTEB"   |"XO MEMBERSHIP" |
      
    @Calculator
    Scenario Outline: [PRIC-3798, RM-1698] Whitelist MMES for N-REG Challenger 300/350 from Insufficient Runway Length
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only NregSmid Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   |arrive    | membertype     |
      | "MMES"   | "KLAX"   |"XO MEMBERSHIP" |
 
 
    @Calculator
    Scenario Outline: [PRIC-3649, RM-1686]Add support for new Airport MMTL
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    And user selects <pax> number
    Then user selects international categories and verifies 'TulumAirport' fee <pax> <fee>
    Examples:
       | origin   | destination  |membertype       | pax | fee    |
       | "MMTL"   | "KLAX"       | "XO MEMBERSHIP" | 0   | "0.00" | 
       | "TJSJ"   | "MMTL"       | "XO MEMBERSHIP" | 0   | "0.00" |   
       
    @Calculator
    Scenario Outline: [PRIC-3800, RM-1688]Fuel Stop Fee for AH SuperLights
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    #LILIAN 07-16-25 begin
    #Then verify it returns fuel Stop
    Then verify it returns fuel Stop <fee>
    Examples:
      | depart   |arrive    | pax | membertype     | fee    |
      | "EGNM"   | "LGAV"   |  1  |"XO MEMBERSHIP" | 3000.00 |
      | "LTFM"   | "EGNM"   |  1  |"XO MEMBERSHIP" | 3000.00 |
    #LILIAN 07-16-25 end
      
  @Calculator
  Scenario Outline: [PRIC-3800, RM-1688]Fuel Stop Fee for AH SuperLights - Round trip
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    #LILIAN 07-16-25 begin
    And user selects <pax> number 
    #LILIAN 07-16-25  end
    Then user clicks calculate button
    Then user clicks view button
    #LILIAN 07-16-25 begin
    #Then verify it returns fuel Stop
    Then verify it returns fuel Stop <fee>
       Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      |pax | fee     | 
      | "EGNM"   | "LGAV"   | "LGAV"    | "LEMD"   |"XO MEMBERSHIP" | 1  | 3000.00|
     #LILIAN 07-16-25 end

     #LILIAN 07-16-25 begin
  #LILIAN 08-03-25 begin
  @Calculator
   Scenario Outline: [PRIC-4904, RM-1869]Change Legacy 600/650 Fuel Stop Fee from eft=12 hours to eft=7 hours - multi leg
   #Scenario Outline: [PRIC-4869, RM-1853]Change Legacy 600/650 Fuel Stop Fee from eft=12 hours to eft=7 hours - multi leg
   #LILIAN 08-03-25 end 
  
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    And select Only AHL600 and AHL650 Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee>
   Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
      #LILIAN 08-03-25 begin
      #| "EGGW"   | "EDDF"   | "EDDF"    | "UAAA"   | "XO MEMBERSHIP" |  1   | 10000.00 |	
      | "EGGW"   | "EDDF"   | "EDDF"    | "UAAA"   | "XO MEMBERSHIP" |  1   | 5000.00 |	
      #LILIAN 08-03-25 end

      
 #LILIAN 08-03-25 begin
  @Calculator
  Scenario Outline: [PRIC-4904, RM-1869]Legacy 600/650 Show Fuel Stop eft >= 7 hrs - MULTI LEG
   #Scenario Outline: [PRIC-4869,RM-1853] Legacy 600/650 Show Fuel Stop eft >= 7 hrs - MULTI LEG
 	#LILIAN 08-03-25 end 
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    And select Only AHL600 and AHL650 Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
      #LILIAN 08-03-25 begin
      #| "EGGW"   | "LIRF"   | "LIRF"    | "KLAX"   | "XO MEMBERSHIP" |  1   | 10000.00 |
      | "EGGW"   | "LIRF"   | "LIRF"    | "KLAX"   | "XO MEMBERSHIP" |  1   | 5000.00 |
      #LILIAN 08-03-25 end	
 
      
#LILIAN 08-03-25  begin   
  @Calculator
  Scenario Outline: [PRIC-4904, RM-1869]Legacy 600/650 Show Fuel Stop eft >= 7 hrs - ROUND TRIP
  #Scenario Outline: [PRIC-4869,RM-1853]Legacy 600/650 Show Fuel Stop eft >= 7 hrs - ROUND TRIP
   #LILIAN 08-03-25 end	
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only AHL600 and AHL650 Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee>
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
       #LILIAN 08-03-25 begin
      #| "EDDF"   | "UAAA"   | "UAAA"    | "EDDF"   | "XO MEMBERSHIP" |  1   | 10000.00 |	
      | "EDDF"   | "UAAA"   | "UAAA"    | "EDDF"   | "XO MEMBERSHIP" |  1   | 5000.00 |	
      | "EGGW"   | "EDDF"   | "EDDF"    | "UAAA"   | "XO MEMBERSHIP" |  1   | 5000.00 |	

      
	#@Calculator
  # Scenario Outline: [PRIC-4869,RM-1853]Legacy 600/650 does not Show Fuel Stop eft < 7 hrs - ROUND TRIP , NEGATIVE TEST    
  #  Given user is on the calculator page
  #  When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
  #  And user selects <membertype> for membershiptype dropdown
  #  And select Only AHL600 and AHL650 Categories
  #  And user selects <pax> number
  #  Then user clicks calculate button
  #  Then user clicks view button
  #  Then verify it returns fuel Stop <fee>
   # Examples:
   #   | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
   #   | "EGGW"   | "EDDF"   | "EDDF"    | "UAAA"   | "XO MEMBERSHIP" |  1   | 10000.00 |
    #LILIAN 08-03-25 end	

      
#LILIAN 08-03-25 begin   
  @Calculator
   Scenario Outline: [PRIC-4904, RM-1869]Legacy 600/650 DOES NOT SHOW Fuel Stop When eft < 7 hrs - multi leg, NEGATIVE TEST
  #Scenario Outline: [PRIC-4869,RM-1853]Legacy 600/650 DOES NOT SHOW Fuel Stop When eft < 7 hrs - multi leg, NEGATIVE TEST
   #LILIAN 08-03-25 end
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    And select Only AHL600 and AHL650 Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it does not return fuel Stop fee
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
    #LILIAN 08-03-25 begin
      #| "EGGW"   | "LIRF"   | "LIRF"    | "OMAA"   | "XO MEMBERSHIP" |  1   | 10000.00 |
      | "EGGW"   | "LIRF"   | "LIRF"    | "OMAA"   | "XO MEMBERSHIP" |  1   | 5000.00 |	
    #LILIAN 08-03-25 end
    
		#LILIAN 07-16-25 end

     
    @Calculator
    Scenario Outline: [PRIC-4503, RM-1690]Add back Stacking Discount for AH Superlights- OW
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user sets date and time for stacking discount <hour> <min> <period>
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns Stacking Discount
    Examples:
      | depart   |arrive    | pax | membertype     |hour| min | period|
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "7"| "00"| "AM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "7"| "30"| "AM"  |
      | "LFMN"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "8"| "00"| "PM"  |
      | "EGLF"   | "LFLB"   |  1  |"XO MEMBERSHIP" | "9"| "00"| "AM"  |
      | "EGLF"   | "LFLB"   |  1  |"XO MEMBERSHIP" | "9"| "30"| "AM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" |"10"| "00"| "AM"  |
      
    @Calculator
    Scenario Outline: [PRIC-4503, RM-1690]Should not return Stacking Discount for AH Superlights for selected times- Negative test
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user sets date and time for stacking discount <hour> <min> <period>
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it does not return Stacking Discount

    Examples:
      | depart   |arrive    | pax | membertype     |hour| min | period|
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" |"6" | "59"| "AM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" |"10"| "01"| "AM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "2"| "00"| "AM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "5"| "00"| "AM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" |"11"| "00"| "AM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" |"12"| "00"| "PM"  |
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "3"| "00"| "PM"  |
      #| "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" | "8"| "00"| "PM"  | Removed as requested by Lillian
      | "EDDF"   | "LIRF"   |  1  |"XO MEMBERSHIP" |"11"| "00"| "PM"  |
      
  @Calculator
  Scenario Outline:  [PRIC-4503, RM-1690]Add back Stacking Discount for AH Superlights - Round trip
    Given user is on the calculator page
		When user enters <depart> <arrive> <hour1> <min1> <period1> and <depart2> <arrive2> <hour2> <min2> <period2> for stacking discount
    And user selects <membertype> for membershiptype dropdown
    And select Only AHPhenom300 and AH CITATION XLS+ Categories
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns Stacking Discount
       Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      |hour1| min1 |period1|hour2|min2 |period2| 
      | "LFMN"   | "LIRF"   | "LIRF"    | "LFMN"   |"XO MEMBERSHIP" |"10" | "00" | "AM"  |"5"  |"00" | "PM"  |
      
      
#LILIAN 06-26-25 end

#LILIAN 07-26-25 begin
   @Calculator
  Scenario Outline: [PRIC-4827, RM-1859]verify OERS (Saudi Arabia) airport returns price for all international categories
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Saudi_Arabia' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |  
      | "LTFM"   | "OERS"       | "XO MEMBERSHIP" |  0   |"0.00" |  
      
  @Calculator
  Scenario Outline: [PRIC-4779, RM-1861]verify Subtotal and Total values are different - international categories
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'Different_Subtotal_Total' fee <pax> <fee>  
    Examples:
      | origin   | destination  |membertype       | pax  |fee    |  
      | "EGGW"   | "LIRF"       | "XO MEMBERSHIP" |  0   |"0.00" |  
      
  @Calculator
  Scenario Outline: [PRIC-4779, RM-1861]verify Subtotal and Total values are different - domestic categories
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    Then user clicks calculate button
    Then user clicks view button
    Then verify subtotal and total values are different
    Examples:
      | depart   | arrive   |  membertype     |
      | "KTEB"   | "KFLL"   | "XO MEMBERSHIP" |
      
    @Calculator
    Scenario Outline: [PRIC-4779, RM-1861]verify Subtotal and Total values are different for US-NonUS 
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only GL6000_GL7500 Categories
    Then user clicks calculate button
    Then user clicks view button
     Then verify subtotal and total values are different
    Examples:
      | depart   | arrive   |  membertype     |
      | "KTEB"   | "EGGW"   | "XO MEMBERSHIP" |
      
		@Calculator
    Scenario Outline: [PRIC-4779, RM-1861]verify Subtotal and Total values are the same for Asia categories
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only Asia Categories
    Then user clicks calculate button
    Then user clicks view button
     Then verify subtotal and total values are the same
    Examples:
      | depart   | arrive   |  membertype     |
      | "VHHH"   | "RJAA"   | "XO MEMBERSHIP" |
   
  
   	@Calculator
    Scenario Outline: verify International Head Tax is $22.90 per leg -one way[FAILED !!!!]
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only CL605_CL850_GL6000_GL7500 Categories
     And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify International Head Tax <tax>
    Examples:
      | depart   | arrive   |  membertype     | pax |   tax  |
      | "KTEB"   | "EGGW"   | "XO MEMBERSHIP" |  1  |  22.90 |
      
   
  @Calculator
  Scenario Outline: verify International Head Tax is $45.80 - round trip
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only CL605_CL850_GL6000_GL7500 Categories
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify International Head Tax <tax> 
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | pax | tax   | 
      | "KTEB"   | "EGGW"   | "EGGW"    | "KTEB"   |"XO MEMBERSHIP" |  1  | 45.80 |
  #LILIAN 07-26-25 end
  
  #LILIAN 08-03-25 begin
  @abc1
  Scenario Outline: [PRIC-3659, RM-1674]verify Wi-Fi Fee was removed for AH categories 
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
   Then select all AH categories and verify Wifi fee was removed
    Examples:
      | origin   | destination  |membertype       |
      | "EGGW"   | "LGAV"       | "XO MEMBERSHIP" |
     
  @abc1
  Scenario Outline: [PRIC-3659, RM-1674]verify Wi-Fi Fee is displayed for VJ categories -Negative test
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then select all VJ categories and verify Wifi fee is displayed
    Examples:
      | origin   | destination  |membertype       |  
      | "EGGW"   | "LGAV"       | "XO MEMBERSHIP" | 
      

    @abc1
    Scenario Outline: [PRIC-4391/4562, RM-1803] Verify it returns Stacking Discount for International Supermid Fleet-CL350[5PM-8PM]
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user sets date and time for stacking discount <hour> <min> <period>
    And select Only CL350 Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns Stacking Discount for CL350
    Examples:
      | depart   |arrive    | pax | membertype     |hour| min | period|
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "5"| "00"| "PM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "6"| "00"| "PM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "7"| "00"| "PM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "7"| "59"| "PM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "8"| "00"| "PM"  |

 
   @abc1
    Scenario Outline: [PRIC-4391/4562, RM-1803] Verify it does not return Stacking Discount for CL350 - NEGATIVE TEST
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user sets date and time for stacking discount <hour> <min> <period>
    And select Only CL350 Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it does not return Stacking Discount
    
    Examples:
      | depart   |arrive    | pax | membertype     |hour| min | period|
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "6"| "00"| "AM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "7"| "00"| "AM"  |
      | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "8"| "01"| "PM"  |
   	  | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" | "9"| "00"| "PM"  |
   	  | "LIRF"   | "LFMN"   |  1  |"XO MEMBERSHIP" |"11"| "00"| "PM"  |
   	   #ASIA
   	  | "RJAA"   | "VHHH"   |  1  |"XO MEMBERSHIP" | "5"| "00"| "PM"  |
      | "RJAA"   | "VHHH"   |  1  |"XO MEMBERSHIP" | "6"| "00"| "AM"  |
  
      
    @abc1
    Scenario Outline: [PRIC-3800, RM-1688]Fix Fuel Stop fees label for N-REG CL350, EFT = 6HRS - One way
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And select Only NregSmid Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee>
    Examples:
      | depart   |arrive    | pax | membertype     | fee     |
      | "KBED"   | "KOAK"   |  1  |"XO MEMBERSHIP" | 3000.00 |
      
 
  @abc1
  Scenario Outline: [PRIC-3800, RM-1688]Fix Fuel Stop fees label for N-REG CL350, 3K PER LEG - Round trip
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only NregSmid Category
    And user selects <pax> number 
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee>
       Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      |pax | fee     | 
      | "KBED"   | "KOAK"   | "KOAK"    | "KBED"   |"XO MEMBERSHIP" | 1  | 3000.00 |
      
  @abc1
  Scenario Outline:  [PRIC-3800, RM-1688]Fix Fuel Stop fees label for N-REG CL350, 3k First leg only - Multi leg
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for multileg
    And user selects <membertype> for membershiptype dropdown
    And select Only NregSmid Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns fuel Stop <fee> for only one leg
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype       |pax   | fee      | 
      | "KBED"   | "KOAK"   | "KOAK"    | "KIAD"   | "XO MEMBERSHIP" |  1   | 3000.00 |	
   
      
    @abc1
    Scenario Outline:[PRIC-3612, RM-1663]Remove OMDB(DUBAI), OMDW, OERK $2000 Other location fee - one way
    Given user is on the calculator page
    When user enters origin <origin> destination <destination>
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'RemoveOMDBAirportFee' fee <pax> <fee>
    Examples:
      | origin   | destination  |membertype       | pax |    fee  |
      | "OMDB"   | "OERK"       | "XO MEMBERSHIP" |  0  | "2000.00" | 
      | "LTFM"   | "OMDB"       | "XO MEMBERSHIP" |  0  | "2000.00" |
      | "OMDW"   | "OERK"       | "XO MEMBERSHIP" |  0  | "2000.00" |  

  @abc1
  Scenario Outline: [PRIC-3612, RM-1663]Remove OMDB(DUBAI), OMDW, OERK $2000 Other location fee - Round trip
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    Then user selects international categories and verifies 'RemoveOMDBAirportFee' fee <pax> <fee>
       Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      |pax | fee     | 
      | "OMDB"   | "LGAV"   | "LGAV"    | "OMDB"   |"XO MEMBERSHIP" | 1  | "2000.00" |
  #LILIAN 08-03-25 end

  #LILIAN 08-15-25 begin
    @abc1
  Scenario Outline: [PRIC-3218, RM-1620] Add KCPP (Greensboro, US) to Pricing Tables- OW domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   | arrive   |  membertype     |
      | "KCPP"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KTEB"   | "KCPP"   | "XO MEMBERSHIP" |
      
  @abc1
  Scenario Outline: [PRIC-3218, RM-1620] Add KCPP (Greensboro, US) to Pricing Tables- RT domestic
    Given user is on the calculator page
    When user enters <depart> and <arrive> and <depart2> and <arrive2> for roundtrip
    And user selects <membertype> for membershiptype dropdown
    And select Only Nregs Category
    Then user clicks calculate button
    Then user clicks view button
 		 Then verify it returns price for the categories
    Examples:
      | depart   | arrive   | depart2   | arrive2  |membertype      | 
      | "KCPP"   | "KTEB"   | "KTEB"    | "KCPP"   |"XO MEMBERSHIP" | 
  
  @abc1
  Scenario Outline: [PRIC-3378, RM-1608] verify KJPX (East Hampton, US) airport returns price- OW domestic
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
    And user selects <membertype> for membershiptype dropdown
    And select Only NregSmid Category
    Then user clicks calculate button
    Then user clicks view button
    Then verify it returns price for the categories
    Examples:
      | depart   | arrive   |  membertype     |
      | "KJPX"   | "KTEB"   | "XO MEMBERSHIP" |
      | "KTEB"   | "KJPX"   | "XO MEMBERSHIP" |
      
    @abc1
    Scenario Outline: [PRIC-3180, RM-1570]Increase Maximum Flight Time for N-reg Fleets
    Given user is on the calculator page
    When user enters origin <depart> destination <arrive>
     And select Only NregUrl Category
    And user selects <pax> number
    Then user clicks calculate button
    Then user clicks view button
    Then verify Flight Time Outside The Aircraft Range Message <depart> <message>
    Examples:
      | depart   |arrive    | membertype     | pax | message 																								|
      | "LFMN"   | "KFLL"   |"XO MEMBERSHIP" | 1   |"Flight Time (10.67h) Outside The Aircraft Range (9.00h)"|
     
  #LILIAN 08-15-25 end
