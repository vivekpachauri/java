
public class Token {
    private final String paranType;
    private final boolean openType;

    public Token(String paranType) {
        if (!paranType.equals("(") && 
            !paranType.equals(")") && 
            !paranType.equals("{") &&
            !paranType.equals("}") &&
            !paranType.equals("[") &&
            !paranType.equals("]")) {
                throw new IllegalArgumentException("wrong input type");
            }
            
        this.paranType = paranType;
        if ( paranType.equals("(") ||
        paranType.equals("[") || 
        paranType.equals("{")) {
            this.openType = true;
        }
        else {
            this.openType = false;
        }
    }


    public String reverse() {
        if (this.paranType.equals("(")) return ")";
        else if (this.paranType.equals("{")) return "}";
        else if (this.paranType.equals("[")) return "]";
        else if (this.paranType.equals(")")) return "(";
        else if (this.paranType.equals("}")) return "{";
        else if (this.paranType.equals("]")) return "[";
        else throw new IllegalArgumentException(this.paranType);
    }
    public String getParanType() {
        return paranType;
    }
    public boolean isOpenType() {
        return openType;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((paranType == null) ? 0 : paranType.hashCode());
        result = prime * result + (openType ? 1231 : 1237);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (paranType == null) {
            if (other.paranType != null)
                return false;
        } else if (!paranType.equals(other.paranType))
            return false;
        if (openType != other.openType)
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Token [paranType=" + paranType + ", openType=" + openType + "]";
    }

    
    
}