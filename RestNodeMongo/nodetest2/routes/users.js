var express = require('express');
var router = express.Router();

/*
 * GET userlist.
 */
router.get('/userlist', function(req, res) {
    var db = req.db;
    var collection = db.get('userlist');
    collection.find({},{},function(e,docs){
        res.json(docs);
    });
});

router.post('/adduser',function(req,res){
var db = req.db;
var collection = db.get('userlist');
collection.insert(req.body,function(err,result){
res.send(
if(err==nulll)?(msg:''):(msg:err));
});
});

module.exports = router;
