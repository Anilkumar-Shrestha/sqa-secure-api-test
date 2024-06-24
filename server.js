const express = require('express');
const dotenv = require('dotenv');
const path = require('path');

// Load environment variables from a specific file path
dotenv.config({ path: path.resolve(__dirname, '.env') });

const server = express();
const port = process.env.PORT;
const accessToken = process.env.ACCESS_TOKEN;

// created App server
server.get('/health', (req, res) => {
console.log(req.query.access_token);
console.log("accessToken ",accessToken);
    if (req.query.access_token == accessToken) {
        res.json({
                     "status": "OK",
                     "code": "200"
                   });
    } else {
    res.status(401).json({
                         "status": "Unauthorized",
                       });
    }
});


server.listen(port, () => {
  console.log(`Server is running at http://localhost:${port}`);
});


