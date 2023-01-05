var _get = _curryr( function(obj, key){
  return obj == null ? undefined : obj[key]
});

function _is_object(obj) {
  return typeof obj == 'object' && !!obj;
}

function _keys(obj) {
  return _is_object(obj) ? Object.keys(obj) : []; 
}

var _length = _get('length');

function _each(list, iter) {
  var keys = _keys(list)

  for (var i = 0, len = keys.length; i < len; i++) {
    iter(list[keys[i]], keys[i]);
  }
  return list;
}

function _map(list, mapper) {
  var new_list = [];

  _each(list, function(val, key) {
    new_list.push(mapper(val, key));
  })
  return new_list;
}

function _filter(list, predicator ) {
  var new_list = [];

  _each(list, function(val) {
    if (predicator(val)) new_list.push(val);
  })
  return new_list;
}

function _curry(fn){
  return function(a, b) {
    return arguments.length == 2 ?  fn(a,b) : function(b) { return fn(a, b);  }; 
  }
}

function _curryr(fn){
  return function(a, b) {
    return arguments.length == 2 ?  fn(a,b) : function(b) { return fn(b, a);  }; 
  }
} 

var _map = _curryr(_map),
  _each = _curryr(_each),
  _filter = _curryr(_filter);


var slice = Array.prototype.slice;
function _rest(list, num){
  return slice.call(list, num || 1);
}

function _reduce(list, iter, memo) {

  if(arguments.length == 2){
    memo = list[0];
    list = _rest(list);
  }

  _each(list, function(val){
    memo = iter(memo, val);
  } )
  return memo;
}

function _pipe() {
  var fns = arguments;
  return function(arg) {
    return _reduce(fns, function(arg, fn){
      return fn(arg);
    }, arg);
  }
}

function _go(arg) {
  var fns = _rest(arguments);
  return _pipe.apply(null, fns)(arg);
}

// map 함수 더 구체화
var _values =  _map(_identity); 
function _identity(val) {
  return val;
}

function _pluck(data, key) {
  return _map(data, _get(key));
}

// filter 함수 좀더 구체화
function _negate(func) {
  return (val) => !func(val);
}

var _reject = _curryr( (data , predi) => _filter(data, _negate(predi)) );

var _compact = _filter(_identity);

// find 함수부터
var _find = _curryr((list, predi) => {
  var keys = _keys(list)

  for (var i = 0, len = keys.length; i < len; i++) {
    var val = list[keys[i]];
    if( predi(val) ) return val;
  }
});

var _find_index = _curryr((list, predi) => {
  var keys = _keys(list)

  for (var i = 0, len = keys.length; i < len; i++) {
    if( predi(list[keys[i]]) ) return i;
  }
  return -1;
});

function _some(data, predi){
  return _find_index(data, predi || _identity) != -1;
}

function _every(data, predi) {
  return _find_index(data, _negate(predi || _identity)) == -1;
}

// reduce 구체화
function _min(data) {
  return _reduce( data, (a, b) =>
    a < b ? a : b
  );
}

function _max(data) {
  return _reduce( data, (a, b) =>
    a > b ? a : b
  );
}

var _min_by = _curryr( (data, iter) => {
  return _reduce( data, (a, b) =>
    iter(a) < iter(b) ? a : b
  );
});

var _max_by = _curryr((data, iter) => {
  return _reduce(data, (a, b) =>
    iter(a) > iter(b) ? a : b
  );
});
// var _min_by = _curryr(_min_by);
// var _max_by = _curryr(_max_by);

// groupBy
function _push(obj, key, value) {
  (obj[key] = obj[key] || []).push(value);
  return obj;
}

var _group_by = _curryr((data, iter) => {
  return _reduce(data, (grouped ,val) => {
    return _push(grouped, iter(val), val);
  }, {})
});

var _first = function(list) {
  return list[0];
}

var _inc = function(count, key) {
  count[key] ?  count[key]++ : count[key] = 1;
  return count;
}

var _count_by = _curryr( (data, iter) => {
  return _reduce( data, (count, val) => {
    return _inc( count, iter(val));
  }, {} )
});

// 

var _pairs = _map( (val, key) => [key, val]);
