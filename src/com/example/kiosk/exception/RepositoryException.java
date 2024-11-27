package com.example.kiosk.exception;

public class RepositoryException extends RuntimeException {
  public static final String DEFAULT_MESSAGE = "해당 객체가 저장소에 존재하지 않습니다!";
  public static final String INDEX_MESSAGE = "%d에 해당하는 객체가 존재하지 않습니다!";
  public static final String NAME_MESSAGE = "%s과 같은 이름을 가진 객체가 존재하지 않습니다!";
  public RepositoryException() {
    super(DEFAULT_MESSAGE);
  }

  public RepositoryException(int index) {
    super(String.format(INDEX_MESSAGE, index));
  }

  public RepositoryException(String name) {
    super(String.format(NAME_MESSAGE, name));
  }
}
