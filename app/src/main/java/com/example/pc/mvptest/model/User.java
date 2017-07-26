package com.example.pc.mvptest.model;

/**
 * @author
 * @version 1.0
 * @date 2017/7/20
 */
public class User {
    Entity status;

    public Entity getStatus() {
        return status;
    }

    public void setStatus(Entity status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "status=" + status +
                '}';
    }

    class Entity{
        String message;
        Double value;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "message='" + message + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
    
}
