package br.com.projetoa3.gui.validations;

public class ValidationsEmailPassword {
    public static void validationsEmail(String email){

        if (email.length() < 5){
            throw new RuntimeException("email invalido");
        } else if(!email.contains("@")){
            throw new RuntimeException("email invalido");
        } else if(!email.contains(".")){
            throw new RuntimeException("email invalido");
        } else if(email.contains("..")){
            throw new RuntimeException("email invalido");
        }  else if(email.length()>254){
            throw new RuntimeException("email invalido");
        }
    }

    public static boolean validationsPassword(String teachPass){
        if(teachPass.trim().length() < 8) {return false;}

        boolean UpperWord = false;
        boolean LowerWord = false;
        boolean Number = false;
        boolean SpecialWord = false;

        for( char c : teachPass.toCharArray()){
            if(Character.isUpperCase(c)){
                UpperWord = true;
            } else if (Character.isLowerCase(c)){
                LowerWord = true;
            } else if(Character.isDigit(c)){
                Number = true;
            } else if(!Character.isLetterOrDigit(c)){
                SpecialWord = true;
            }

        }
        return UpperWord && LowerWord && Number && SpecialWord;
    }
}
