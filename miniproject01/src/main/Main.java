package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Controller;

import javazoom.jl.player.MP3Player;

import model.CharacterDAO;
import model.CharacterDTO;

import model.DTO;
import model.Music;
import model.MusicPlayer;
import model.Random1;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println(
				"                ` `   ```````                        ` `                               ```                          ```  `                                  \r\n"
						+ "                  ````````##                         ``##`                           ``##`                          `###``                                  \r\n"
						+ " `  ``            ###   ``##    ` ##############    ` `##`      ```  ``###`` ```     ``##`   ` `########`#########   ###`         `####################` `  \r\n"
						+ "` ###     ``##`   ###   ``##`     ##`        ```    ` `##`      ` `#####``####`      ``##`   ``````###``````###````  ### `       ``                 ### `   \r\n"
						+ "  ##`     ` ##`   ###   ``##     `##`               ` `##`   `` ``###`` `   `### ` ` ``##`      ` `##``    `##`      ###```                         ###     \r\n"
						+ "  ##` `   ` ##``  ###   ``##     `##`               ` `#######```###`         ##``   ``##`       `####   ```##`      ########                       ##`     \r\n"
						+ "  ##` `   ` ##``  ###   ``##     `##`               ` `##`   `  `##`          ###    ``##`       `####`   `####`     ###``  `                       ##`     \r\n"
						+ "  ############``  ##########     `##``` ``  ``````#######`       ### ``    `  ##`    ``##`     `###``###``### ##` `  ###                        ` ``##      \r\n"
						+ "  ##` `   ` ##``  ###`````##      #################`` `##`      ``###````` ``###     ``##`    `###``` `#####   ###`  ###      `                    ###      \r\n"
						+ "  ##` `   ` ##``  ###  ` `##    `      `` `  `   ` ` ` `        `  `########## `     ``##`    ``` ` ` ` `#    `  ``  ###     `##############################\r\n"
						+ "  ##` `   ` ##``  ###   ``##          ####################          `  ``````        ``##`          ` ``  ```````` `  `  `     `            ##``            \r\n"
						+ "  ##` `   ` ##``  ###   ``##         ``                ###          `   ` `          ``##`           ``###############   `                  ##``           `\r\n"
						+ "  ############    ###   ``##        ``                 ###     ````````  ` ``    ```` `##`          `###`    ``` ` `####`                   ##`             \r\n"
						+ "                  ###   ``##         `####################   ` `#################### ``##`          ###              `###`                  ##`             \r\n"
						+ "                  ###   ``##         `###`             `       `            `  ``    ``##`          ###`            ``##`                   ##`             \r\n"
						+ "                  ###`  ``##         `###````````````````` `                         ``##`           `####` ` `  `#####``                   ##`             \r\n"
						+ "                         `##        ` ####################                          ` `##`             ``########### `                     `##`             ");

//		MusicPlayer musicPlayer = new MusicPlayer();
		CharacterDTO cDTO = new CharacterDTO(null, 0, 0, 0);
		CharacterDAO cDAO = new CharacterDAO();
		Controller controller = new Controller();
		Random1 r = new Random1();
		DTO dto = null;
		int num = 0;
		while (true) {

//			musicPlayer.playMusic("BGM1"); // 필요한 브금을 삽입, 시작

//			musicPlayer.playMusic("BGM1"); // 필요한 브금을 삽입, 시작(음악이 없는 상태에서 Ctrl + f11하면 오류남)
			System.out.print("[1]회원가입  [2]로그인  [3]랭킹  [4]게임종료 >> ");
			int menu = sc.nextInt();
			if (menu == 1) { // 회원가입
				System.out.println("회원 등록");
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				String pw = sc.next();
				System.out.print("닉네임 입력 : ");
				String nick = sc.next();
				dto = new DTO(id, pw, nick);
				int cnt = controller.join(dto);
//				musicPlayer.stopMusic("BGM1"); // 다 입력하면 브금 종료 -> 필요한 곳에 필요한 브금만 출력되도록 하기 위한 조치

			} else if (menu == 2) { // 로그인
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				String pw = sc.next();

				DTO info = controller.login(id, pw);

				if (info == null) {
					System.out.println("로그인 실패");
				} else if (info != null) {
					String name = info.getNick();
					for (int i = 0; i < name.length(); i++) {
						System.out.print(name.charAt(i));
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					System.out.println("님 환영합니다.");

					int life = r.random4(0);
					String ya = "하루최대 배달 가능횟수 : " + life + "회";
					for (int i = 0; i < ya.length(); i++) {
						System.out.print(ya.charAt(i));
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println();

					while (life > 0) { // 게임진행
						System.out
								.print("경로선택 >> [1]떡잎마을(Hard) [2]떡잎 유치원(Easy) [3]캐릭터정보 [4]초코비먹기 [5]잠자기 [6]로비로 돌아가기>> ");
						num = sc.nextInt();

						if (num == 1) { // 떡잎마을

							CharacterDTO cInfo = cDAO.login(id, pw);
							cDTO = cInfo;
							cDAO.delivery1(cDTO);
							cInfo = cDAO.login(id, pw);
							System.out.println("떡잎마을 배달시작");
							System.out.println(r.random3(0));
							System.out.println("남은 배달횟수 : " + cInfo.getLife());

						} else if (num == 2) { // 떡잎유치원
							CharacterDTO cInfo = cDAO.login(id, pw);
							cDTO = cInfo;
							cDAO.delivery2(cDTO);
							cInfo = cDAO.login(id, pw);
							System.out.println("떡잎 유치원 배달시작");
							System.out.println(r.random2(0));
							
							System.out.println("남은 체력 : " + cInfo.getHp());
							System.out.println("남은 배달횟수 : " + cInfo.getLife());

						} else if (num == 3) { // 캐릭터정보
							CharacterDTO cInfo = cDAO.login(id, pw);
							System.out.println("레벨 : " + cInfo.getLevel());
							System.out.println("HP : " + cInfo.getHp());
							System.out.println("초코비 : " + cInfo.getCb());
							System.out.println("남은 배달횟수 : " + cInfo.getLife());
						} else if (num == 4) { // 초코비 먹기
							CharacterDTO Info = cDAO.login(id,pw);
							cDTO = Info;
							life++; // 배달횟수 1회 추가
							cDAO.eat(cDTO);// 체력 3회복
							String a = "초코비를 먹어 체력과 배달횟수가 회복됩니다.";
							for (int i = 0; i < a.length(); i++) {
								System.out.print(a.charAt(i));
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println();
							System.out.println("남은 초코비 개수 : ");
							System.out.println("체력 + 3, 배달횟수 + 1");
						} else if (num == 5) { // 잠자기
							cDAO.sleep(cDTO);
							life = r.random4(0);
							String a = "자는중....              ";
							for (int i = 0; i < a.length(); i++) {
								System.out.print(a.charAt(i));
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println();
							System.out.println("배달 가능횟수가 초기화 되었습니다.");
							System.out.println("남은 배달횟수 : " + life);
						} else if (num == 6) { // 뒤로가기
							String end = "로비로 돌아가는중...";
							for (int i = 0; i < end.length(); i++) {
								System.out.print(end.charAt(i));

								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println();
							break;
						} else {
							System.out.println("잘못 입력하셨습니다.");
						}

						if (life <= 0) {
							String end = "남은 배달 횟수를 모두 소진하여 종료합니다.";
							for (int i = 0; i < end.length(); i++) {
								System.out.print(end.charAt(i));
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							System.out.println();

						}
					}
				}
			} else if (menu == 3) { // 랭킹
				for (int i = 0; i < 10; i++) {
					System.out.println((i + 1) + "위 : " + "\t 레벨 : ");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			} else if (menu == 4) { // 게임종료
				System.out.println(
						"\r\n" + "\r\n" + "                                ,~:!****!:~,.                         \r\n"
								+ "                             .!#@@@@@@@@@@@@@=,                       \r\n"
								+ "                           ,=@@@=;:;!*=$@@@@@@@=~                     \r\n"
								+ "                          :@@@@@@;      .,;@@@@@@=,                   \r\n"
								+ "                         !#@@@@@@@*      ;#@@@@@@@@;                  \r\n"
								+ "                        *@@@@*:#@@@-    =@@@@@@@@@@@*                 \r\n"
								+ "                       :@@@@!  -@@@@   .@@@@#;,$@@@@@=                \r\n"
								+ "                       !@@@:    -@@@   .#@@=,  .=@@@@@*               \r\n"
								+ "                      :.!=~,~~~-~;*,    .;:,~~~~:#@@!;@;              \r\n"
								+ "          ,;          ;   :-     !        ;~     =*:  !@,             \r\n"
								+ "          -.-  -      ~  :,       :      ;.       ;,  :@=             \r\n"
								+ "       .. ,,~.:~     ., ,-    ..  .-    -- ..      :  ,@@~            \r\n"
								+ "       ~-*,: !;-     .. $   -$@@@: =    ! =@@$~    ,:  @@=            \r\n"
								+ "       ~~ -, ; ,     .. ! .*@@@@@@=-    ;#@@@@@!    !  =@#.           \r\n"
								+ "        ~-   , -    .-.   *@@@#$#@@:   ,@@@##@@@-   .  ;@@:           \r\n"
								+ "      ~;:~    .,   :,    -@@@#. .@@@   *@@!..#@@$      -@@$           \r\n"
								+ "      -~~,    .-  ~      !@@@-   $@@.  @@@   ;@@@.     .@#~-:         \r\n"
								+ "         *.     ;!       *@@@-  ,@@@  .@@@  .#@@@.     .#.  .:        \r\n"
								+ "        ;.-;=,  :.       ;@@@@$$@@@=   @@@#$#@@@#      ,-    ,-       \r\n"
								+ "        ;!- ,, ,-         $@@@@@@@@,   ;@@@@@@@@~      :      ~       \r\n"
								+ "             ; ~           #@@@@@@-     *@@@@@@;              -.      \r\n"
								+ "             ~ ~            ,~~~~.       .,~~-.               .~      \r\n"
								+ "             --,                                               !      \r\n"
								+ "              $                                                *      \r\n"
								+ "              ~.                                              .~      \r\n"
								+ "              ,,                                      ,,.     -       \r\n"
								+ "               -                                   ,*!..-!~,,;,       \r\n"
								+ "               ~                                ~:;!.,:~::.,,         \r\n"
								+ "               ,~                             .!~ .......,,           \r\n"
								+ "                --                         -;~-..........,,           \r\n"
								+ "                .!-                    .,~;~..............~           \r\n"
								+ "                ~.,#.        .      .!$~,.................;           \r\n"
								+ "                !...~:!~     - .!!::-......................-          \r\n"
								+ "                .:,....-!*!*!*!~...........................~          \r\n"
								+ "                  ~~....,..................................~,         \r\n"
								+ "                   .:-.:....................................!         \r\n"
								+ "                     ,::.........................-..........-.        \r\n"
								+ "                      .;.........................~,.......,:*.        \r\n"
								+ "                      :,.........................-,....-~:*.          \r\n"
								+ "                      *..........................-,.,;*-  ;           \r\n"
								+ "                      :..........................,;:-!    ;           \r\n"
								+ "                     .-..............................~.   !           \r\n"
								+ "                     ,,..............................,-   *           \r\n"
								+ "                     -,..............................,:  .;           \r\n"
								+ "                     ;...............................-~, -,           \r\n"
								+ "                     ;...............................:.; :            \r\n"
								+ "                     ;..........................,,..,:~, ~            \r\n"
								+ "                     ;......~;!-....,!;;,....,:;;;!;!,   -.           \r\n"
								+ "                     -;::::,.  ,;;:-.   -;:;:-      .=   :            \r\n"
								+ "                     -.                   ..         ~~,~:            \r\n"
								+ "                     ~                                :.              \r\n"
								+ "                     :                                :               \r\n" + "");

				break;
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}

		}

	}

}
