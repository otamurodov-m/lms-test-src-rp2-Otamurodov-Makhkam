package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//goToメソッドの呼び出し（画面遷移）
		goTo("http://localhost:8080/lms");
		//タイトルチェック
		assertEquals("ログイン | LMS", webDriver.getTitle());
		//getEvidenceメソッドの呼び出し（エビデンス取得）
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//存在しないIDとパスワードを入力
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA04");
		webDriver.findElement(By.name("password")).sendKeys("StudentAA044");
		//ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		//画面遷移後URLを取得し、コース詳細画面のURLと比較
		assertEquals("http://localhost:8080/lms/course/detail", webDriver.getCurrentUrl());
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//ドロップダウンボタンをクリック
		webDriver.findElement(By.className("dropdown-toggle")).click();
		//「ヘルプ」リンクをクリック処理
		webDriver.findElement(By.linkText("ヘルプ")).click();
		assertEquals("http://localhost:8080/lms/help", webDriver.getCurrentUrl());
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 元のタブのIDを取得
		String originalWindow = webDriver.getWindowHandle();
		webDriver.findElement(By.linkText("よくある質問")).click();
		// 新しいタブに操作対象を切り替える
		for (String windowHandle : webDriver.getWindowHandles()) {
			if (!originalWindow.equals(windowHandle)) {
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}
		assertEquals("http://localhost:8080/lms/faq", webDriver.getCurrentUrl());
		getEvidence(new Object() {
		});
	}

}
