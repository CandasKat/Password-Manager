import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class NewObjectoutputStream extends ObjectOutputStream {

    NewObjectoutputStream() throws IOException, SecurityException {
        super();
    }

    NewObjectoutputStream(OutputStream outputStream) throws IOException {
        super(outputStream);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        return;
    }
}
