/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 */
function handleTree(data, id, parentId, children) {
  let config = {
    id: id || 'id',
    parentId: parentId || 'parentId',
    childrenList: children || 'children'
  };

  var childrenListMap = {};  // map: parend_id -> array of node_data
  var nodeMap = {};          // map: id        -> node_data
  var forest = [];

  for (let item of data) {
    let parentId = item["parentId"];
    if (childrenListMap[parentId] == null) {
      childrenListMap[parentId] = [];
    }
    nodeMap[item["id"]] = item;
    childrenListMap[parentId].push(item);
  }

  for (let item of data) {
    let parentId = item["parentId"];
    if (nodeMap[parentId] == null) {
      forest.push(item);
    }
  }

  for (let root of forest) {
    adaptToChildrenList(root);
  }

  function adaptToChildrenList(root) {
    if (childrenListMap[root["id"]] !== null) {
      root['children'] = childrenListMap[root["id"]];
    }
    if (root['children']) {
      for (let child of root['children']) {
        adaptToChildrenList(child);
      }
    }
  }
  return forest;
}
